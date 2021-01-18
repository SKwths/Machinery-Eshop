package bjfu.six.mall.mapper;

import bjfu.six.mall.entity.po.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ItemsMapper {
    @Select("select * from action_order_items where order_id = #{order_id}")
    Item[] getItemsByOrderId(int order_id);
    @Insert("insert into action_order_items(id,uid,order_id,goods_id,goods_name,icon_url,price,quantity,total_price)" +
            " values(#{id},#{uid},#{orderId},#{goodsId},#{goodsName},#{iconUrl},#{price},#{quantity},#{totalPrice})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    int insertItem(Item item);
}
