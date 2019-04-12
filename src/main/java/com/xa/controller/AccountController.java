package com.xa.controller;

import com.xa.constant.StatusCodeConstant;
import com.xa.pojo.Account;
import com.xa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @RequestMapping("/login")
    public String login(Account account, Model model, HttpSession session){

        //假设前段验证通过
        boolean existsCardNo = accountService.existsCardNo(account.getCardNo());
        if(existsCardNo){
            //卡号密码登录
            account = accountService.login(account);
            if(account == null){
                //密码错误
                model.addAttribute("errorMsg","密码错误");
                return "index";
            }else{
                //检查状态
                if(account.getStatus() == 0){
                    //冻结
                    model.addAttribute("errorMsg","账号被冻结");
                    return "index";
                }else {
                    //登录成功
                    session.setAttribute("account",account);
                    return "main";
                }
            }
        }else{
            //卡号不存在
            model.addAttribute("errorMsg","卡号不存在");
            return "index";
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("account");
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/balance/{cardNo}")
    public String queryBalance(@PathVariable("cardNo")String cardNo,Model model){
        double balance = accountService.queryBalance(cardNo);
        model.addAttribute("balance",balance);
        model.addAttribute("page","balance");
        return "main";
    }

    @RequestMapping("/transferTo")
    public String transferTo(Model model){
        model.addAttribute("page","transfer");
        return "main";
    }

    @RequestMapping("/transfer")
    public String transfer(Model model,@RequestParam("cardNo")String cardNo,@RequestParam("transactionAmount")Double transactionAmount ,HttpSession session){
        Account sourceAccount = (Account) session.getAttribute("account");
        Map<String,Object> map = accountService.transfer(sourceAccount,cardNo,transactionAmount);
        int code = (int) map.get("code");
        model.addAttribute("code",code);
        String msg = (String) map.get("msg");
        model.addAttribute("msg",msg);
        model.addAttribute("page","transfer");
//        if(code == StatusCodeConstant.SUCCESS){
//            //成功的响应
//        }else{
//            //失败的响应
//
//        }
        return "main";
    }
}
