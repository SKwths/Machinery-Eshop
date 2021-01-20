package bjfu.six.mall.service;


import bjfu.six.mall.entity.po.Cart;
import bjfu.six.mall.entity.po.Products;
import bjfu.six.mall.entity.vo.CartList;
import bjfu.six.mall.entity.vo.Lists;
import bjfu.six.mall.mapper.CartsMapper;
import bjfu.six.mall.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartsMapper cartsMapper;
    @Autowired
    private ProductsMapper productsMapper;

    public int getquantity(int userId){
        ArrayList<Integer> quantity=cartsMapper.selectQuantityByuserId(userId);
        int totalquantity=0;
        for(int i=0;i<quantity.size();i++){
            totalquantity+=quantity.get(i);
        }
        return totalquantity;
    }

    public CartList updateCommodity(int userId, int productId, int count){
        //更新商品的数量
        cartsMapper.updateCommodityQuantity(userId,productId,count);
        //返回商品信息
        Cart cart=cartsMapper.selectCartByuserIdAndproductId(userId,productId);
        Products commodity;

        Lists[] lists=new Lists[1];

        lists[0]=new Lists();
        lists[0].setId(cart.getId());
        lists[0].setUserId(userId);
            commodity= productsMapper.selectProductsById(cart.getProductId());
        lists[0].setProductId(cart.getProductId());
        lists[0].setName(commodity.getName());
        lists[0].setQuantity(cart.getQuantity());
        lists[0].setPrice(commodity.getPrice());
        lists[0].setStatus(commodity.getStatus());
        lists[0].setTotalPrice(commodity.getPrice()*cart.getQuantity());
        lists[0].setStock(commodity.getStock());
        lists[0].setIconUrl(commodity.getIconUrl());

        CartList cartList=new CartList();
        cartList.setLists(lists);
        cartList.setTotalPrice(lists[0].getTotalPrice());

        return cartList;

    }

    public void clearCart(int userId){
        cartsMapper.clearCart(userId);
    }

    public CartList delCommodity(int userId,int productId) {
        cartsMapper.deleteCommodity(userId, productId);
        Cart[] cart;
        cart = cartsMapper.selectCartByUserId(userId);
        Products commodity;
        Lists[] lists = new Lists[cart.length];
        double totalPrice = 0;
        for (int i = 0; i < cart.length; i++) {
            lists[i] = new Lists();
            lists[i].setId(cart[i].getId());
            lists[i].setUserId(userId);
            commodity = productsMapper.selectProductsById(cart[i].getProductId());
            lists[i].setProductId(cart[i].getProductId());
            lists[i].setName(commodity.getName());
            lists[i].setQuantity(cart[i].getQuantity());
            lists[i].setPrice(commodity.getPrice());
            lists[i].setStatus(commodity.getStatus());
            lists[i].setTotalPrice(commodity.getPrice() * cart[i].getQuantity());
            lists[i].setStock(commodity.getStock());
            lists[i].setIconUrl(commodity.getIconUrl());
            totalPrice += lists[i].getTotalPrice();
        }
        CartList cartList = new CartList();
        cartList.setLists(lists);
        cartList.setTotalPrice(totalPrice);
        return cartList;
    }

    public CartList findAllCart(int userId){
        Cart[] cart=cartsMapper.selectCartByUserId(userId);
        Lists[] lists=new Lists[cart.length];
        double totalPrice=0;
        for (int i = 0; i <cart.length ; i++) {
            lists[i]=new Lists();
            lists[i].setId(cart[i].getId());
            lists[i].setUserId(userId);
            Products commodity= productsMapper.selectProductsById(cart[i].getProductId());
            lists[i].setProductId(cart[i].getProductId());
            lists[i].setName(commodity.getName());
            lists[i].setQuantity(cart[i].getQuantity());
            lists[i].setPrice(commodity.getPrice());
            lists[i].setStatus(commodity.getStatus());
            lists[i].setTotalPrice(commodity.getPrice()*cart[i].getQuantity());
            lists[i].setStock(commodity.getStock());
            lists[i].setIconUrl(commodity.getIconUrl());
            totalPrice+=lists[i].getTotalPrice();
        }

        CartList cartList=new CartList();
        cartList.setLists(lists);
        cartList.setTotalPrice(totalPrice);
        return cartList;
    }

    public void addCommodity(int userId,int productId,int count){
        Cart cartInSystem = cartsMapper.selectCartByuserIdAndproductId(userId, productId);
        if(cartInSystem != null){
            int quantityInSystem=cartInSystem.getQuantity();
            cartsMapper.updateCommodityQuantity(userId, productId, count+quantityInSystem);
        }
        else {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(count);
            cartsMapper.insertCart(cart);
        }
    }


    public Cart[] findCartsByUserId(int userId){
        return cartsMapper.selectCartByUserId(userId);
    }

}
