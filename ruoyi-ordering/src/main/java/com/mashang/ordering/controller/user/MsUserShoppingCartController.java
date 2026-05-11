package com.mashang.ordering.controller.user;

import com.mashang.ordering.domain.entity.MsProduct;
import com.mashang.ordering.domain.entity.MsShoppingCart;
import com.mashang.ordering.domain.entity.MsSpecificationValue;
import com.mashang.ordering.domain.param.create.MsUserShoppingCartCreate;
import com.mashang.ordering.domain.vo.MsUserShoppingCartListVo;
import com.mashang.ordering.domain.vo.MsUserShoppingCartVo;
import com.mashang.ordering.mapper.MsSpecificationValueMapper;
import com.mashang.ordering.mapping.MsShoppingCartMapping;
import com.mashang.ordering.service.IMsProductService;
import com.mashang.ordering.service.IMsSpecificationService;
import com.mashang.ordering.service.IMsUserShoppingCartService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-shopingcart")
@Api(tags = "用户端-购物车管理")
public class MsUserShoppingCartController extends BaseController {

    @Autowired
    private IMsUserShoppingCartService msUserShoppingCartService;

    @Autowired
    private MsSpecificationValueMapper msSpecificationValueMapper;

    @Autowired
    private IMsProductService msProductService;

    @GetMapping
    @ApiOperation("查询购物车列表")
    public R<MsUserShoppingCartVo> list(Long userId,Long storeId){
        MsUserShoppingCartVo vo = new MsUserShoppingCartVo();

        List<MsUserShoppingCartListVo> list = msUserShoppingCartService.getList(userId,storeId);

        vo.setMsUserShoppingCartListVos(list);
        Double sum= 0d;
        for (MsUserShoppingCartListVo msUserShoppingCartListVo : list) {
            sum+=msUserShoppingCartListVo.getTotalAmount();
        }
        vo.setTotalPrice(sum);
        return R.ok(vo);
    }

    @PostMapping
    @ApiOperation("新增购物车")
    public R add(@RequestBody @Validated MsUserShoppingCartCreate msUserShoppingCartCreate){

        MsProduct product = msProductService.getById(msUserShoppingCartCreate.getProductId());
        msUserShoppingCartCreate.setProductImage(product.getProductCover());
        msUserShoppingCartCreate.setProductName(product.getProductName());
        msUserShoppingCartCreate.setProductPrice(product.getProductPrice());
        msUserShoppingCartCreate.setTotalAmount(product.getProductPrice()*msUserShoppingCartCreate.getProductQuantity());

        msUserShoppingCartCreate.setUserId(SecurityUtils.getUserId());
        MsShoppingCart create = MsShoppingCartMapping.INSTANCE.toCreate(msUserShoppingCartCreate);
        StringBuilder specification= new StringBuilder();
        int tmp=0;
        for (Long id: msUserShoppingCartCreate.getSpecificationIdLIst()){
            if(tmp!=0){
                specification.append(";");
            }
            MsSpecificationValue msSpecificationValue = msSpecificationValueMapper.selectById(id);
            specification.append(msSpecificationValue.getSpecs());
            tmp++;
        }
        create.setSpecification(specification.toString());
        msUserShoppingCartService.save(create);
        return R.ok();
    }

    @DeleteMapping("/{storeCategoriesId}")
    @ApiOperation("删除购物车中的信息")
    public R delete(@Validated @PathVariable Long storeCategoriesId){
        msUserShoppingCartService.removeById(storeCategoriesId);
        return R.ok();
    }

    @DeleteMapping("/clear")
    @ApiOperation("清空购物车")
    public R deleteAll(){
        msUserShoppingCartService.deletAll(SecurityUtils.getUserId());
        return R.ok();
    }


}
