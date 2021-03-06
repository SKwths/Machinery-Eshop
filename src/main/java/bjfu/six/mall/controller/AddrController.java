package bjfu.six.mall.controller;

import bjfu.six.mall.common.Response;
import bjfu.six.mall.entity.po.Address;
import bjfu.six.mall.entity.po.User;
import bjfu.six.mall.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AddrController {
    @Autowired
    private AddrService addrService;

    @RequestMapping("/addr/saveaddr.do")
    @ResponseBody
    public Response saveAddr(String name, String mobile, String province, String city, String district, String addr, String zip, HttpSession session)
    {
        User user = (User)session.getAttribute("user");

        if(user!=null)
        {
            int user_id = user.getId();
            Address address[] = addrService.saveAddr(name,mobile,province,city,district,addr,zip,user_id);
            Response rs = new Response();
            rs.setData(address);
            return rs;
        }
        return Response.error(1, "地址增加失败");
    }

    @RequestMapping("/addr/deladdr.do")
    @ResponseBody
    public Response delAddr(String id,HttpSession session)
    {
        User user = (User)session.getAttribute("user");

        if(user!=null)
        {
            int user_id = user.getId();
            int Delid = Integer.valueOf(id).intValue();
            Response rs = new Response();
            Address address[] = addrService.delAddr(Delid);
            rs.setData(address);
            return rs;
        }
        return Response.error(1,"请登录后再操作");
    }

    @RequestMapping("/addr/findaddrs.do")
    @ResponseBody
    public Response findAddr(HttpSession session)
    {
        User user = (User)session.getAttribute("user");

        if(user!=null)
        {
            int user_id = user.getId();
            Response rs = new Response();
            Address address[] = addrService.findAddr(user_id);
            rs.setData(address);
            return rs;
        }
        return Response.error(1,"请登录之后再进行操作");
    }

    @RequestMapping("/addr/setdefault.do")
    @ResponseBody
    public Response setDefaultAddr(String id,HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user!=null)
        {
            int user_id = user.getId();
            int setid = Integer.valueOf(id).intValue();
            Response rs = new Response();
            Address address[] = addrService.setDefaultAddr(setid,user_id);
            rs.setData(address);
            return rs;
        }
        return Response.error(1,"请登录之后再进行操作");
    }

    @RequestMapping("/addr/findAddressById.do")
    @ResponseBody
    public Response findAddressById(String id,HttpSession session)
    {
        User user = (User)session.getAttribute("user");

        if(user!=null)
        {
            int user_id = user.getId();
            int setid = Integer.valueOf(id).intValue();
            Response rs = new Response();
            Address address = addrService.findAddressById(setid);
            System.out.println(address.getId());
            System.out.println(address.getDefaultAddr());
            rs.setData(address);
            return rs;
        }
        return Response.error(1,"参数错误");
    }
}
