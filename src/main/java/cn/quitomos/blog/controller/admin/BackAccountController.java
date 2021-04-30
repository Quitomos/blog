package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.entity.Account;
import cn.quitomos.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 后台外链账户设置
 */
@Controller
@RequestMapping("/admin/account")
public class BackAccountController {

    private AccountService accountService;

    /**
     * 外链账户首页
     *
     * @param model 需给出accountList
     * @return index.jsp
     */
    @RequestMapping("")
    public String account(Model model) {
        List<Account> accountList = accountService.list();
        model.addAttribute("accountList", accountList);
        return "Admin/Account/index";
    }

    /**
     * 添加外链账户提交
     *
     * @param account account
     * @return /account
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(Account account) {
        accountService.saveAccount(account);
        return "redirect:/admin/account";
    }

    /**
     * 删除外链账户
     *
     * @param id accountId
     * @return /account
     */
    @RequestMapping("/delete")
    public String deleteAccount(@RequestParam("accountid") Integer id) {
        accountService.deleteAccount(id);
        return "redirect:/admin/account";
    }

    /**
     * 修改外链账户
     *
     * @param id accountId
     * @param model 需要提供原account, accountList
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String editAccount(@RequestParam("accountid") Integer id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        List<Account> accountList = accountService.list();
        model.addAttribute("accountList", accountList);
        return "Admin/Account/edit";
    }

    /**
     * 提交修改
     *
     * @param account account
     * @return /account
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(Account account) {
        accountService.saveAccount(account);
        return "redirect:/admin/account";
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
