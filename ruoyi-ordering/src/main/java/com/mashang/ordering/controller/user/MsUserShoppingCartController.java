package com.mashang.ordering.controller.user;

import com.mashang.ordering.domain.param.create.MsUserShoppingCartCreate;
import com.mashang.ordering.domain.vo.MsUserShoppingCartListVo;
import com.mashang.ordering.domain.vo.MsUserShoppingCartVo;
import com.mashang.ordering.mapping.MsShoppingCartMapping;
import com.mashang.ordering.service.IMsUserShoppingCartService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
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
        msUserShoppingCartService.save(MsShoppingCartMapping.INSTANCE.toCreate(msUserShoppingCartCreate));
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
    public R deleteAll(Long userId){
        msUserShoppingCartService.deletAll(userId);
        return R.ok();
    }


}
