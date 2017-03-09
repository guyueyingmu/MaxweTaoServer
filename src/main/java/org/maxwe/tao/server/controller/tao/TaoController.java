package org.maxwe.tao.server.controller.tao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import org.apache.log4j.Logger;
import org.maxwe.tao.server.common.response.ResponseModel;
import org.maxwe.tao.server.controller.account.model.TokenModel;
import org.maxwe.tao.server.controller.tao.model.alimama.*;
import org.maxwe.tao.server.interceptor.AppInterceptor;
import org.maxwe.tao.server.interceptor.TokenInterceptor;
import org.maxwe.tao.server.service.tao.alimama.brand.BrandServices;
import org.maxwe.tao.server.service.tao.alimama.brand.GuideEntity;
import org.maxwe.tao.server.service.tao.alimama.common.AliResponsePageEntity;
import org.maxwe.tao.server.service.tao.alimama.convert.AliConvertEntity;
import org.maxwe.tao.server.service.tao.alimama.convert.AliConvertRequestModel;
import org.maxwe.tao.server.service.tao.alimama.convert.AliConvertServices;
import org.maxwe.tao.server.service.tao.alimama.goods.AliGoodsRequestModel;
import org.maxwe.tao.server.service.tao.alimama.goods.GoodsServices;

import java.util.List;

/**
 * Created by Pengwei Ding on 2017-02-11 12:29.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description:
 */
public class TaoController extends Controller implements ITaoController {
    private final Logger logger = Logger.getLogger(TaoController.class.getName());

    @Override
    @Before({AppInterceptor.class, TokenInterceptor.class})
    public void search() {
        String params = this.getAttr("p");
        logger.info("search : params = " + params);
        GoodsRequestModel requestModel = JSON.parseObject(params, GoodsRequestModel.class);
        if (!requestModel.isGoodsRequestParamsOk()) {
            logger.info("search : 参数错误 params = " + params);
            GoodsResponseModel responseModel = new GoodsResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_BAD_PARAMS);
            responseModel.setMessage("参数错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            return;
        }

        AliGoodsRequestModel aliGoodsRequestModel = new AliGoodsRequestModel();
        aliGoodsRequestModel.setToPage(requestModel.getToPage());
        aliGoodsRequestModel.setPerPageSize(requestModel.getPerPageSize());
        aliGoodsRequestModel.setQ(requestModel.getQ());
        aliGoodsRequestModel.setCookie(requestModel.getCookie());
        aliGoodsRequestModel.setSortType(requestModel.getSortType());
        aliGoodsRequestModel.setUrlType(requestModel.getUrlType());

        try {
            logger.info("search : 查询条件 " + requestModel.toString());
            List<AliResponsePageEntity> aliResponsePageEntities = GoodsServices.searchForGoods(aliGoodsRequestModel);
            if (aliResponsePageEntities == null) {
                logger.info("search : 查询结果 null ");
                GoodsResponseModel responseModel = new GoodsResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_NOT_FOUND);
                responseModel.setMessage("没有数据，请更换查询参数");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
                return;
            }
            if (aliResponsePageEntities.size() < 1) {
                GoodsResponseModel responseModel = new GoodsResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_EMPTY);
                responseModel.setMessage("没有数据，请更换查询参数");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            } else {
                logger.info("search : 查询结果总量 " + aliResponsePageEntities.size());
                logger.debug("search : 查询结果信息 " + aliResponsePageEntities.toString());
                GoodsResponseModel responseModel = new GoodsResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_SUCCESS);
                responseModel.setMessage("查询成功");
                responseModel.setGoodsEntities(aliResponsePageEntities);
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            }
        } catch (Exception e) {
            logger.info("search : 查询结果异常");
            e.printStackTrace();
            GoodsResponseModel responseModel = new GoodsResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_SERVER_ERROR);
            responseModel.setMessage("查询错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
        }
    }

    @Override
    @Before({AppInterceptor.class, TokenInterceptor.class})
    public void auction() {
        String params = this.getAttr("p");
        AuctionRequestModel requestModel = JSON.parseObject(params, AuctionRequestModel.class);
        if (requestModel == null || !requestModel.isAuctionRequestParamsOk()) {
            logger.info("auction : 参数错误 params = " + params);
            AuctionResponseModel responseModel = new AuctionResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_BAD_PARAMS);
            responseModel.setMessage("参数错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            return;
        }

        AliConvertRequestModel aliConvertRequestModel = new AliConvertRequestModel();
        aliConvertRequestModel.setSiteid(requestModel.getSiteid());
        aliConvertRequestModel.setAuctionid(requestModel.getAuctionid());
        aliConvertRequestModel.setAdzoneid(requestModel.getAdzoneid());
        aliConvertRequestModel.setCookie(requestModel.getCookie());

        try {
            AliConvertEntity aliConvertEntity = AliConvertServices.convertAlimamaByGoodsId(aliConvertRequestModel);
            if (aliConvertEntity == null) {
                logger.info("auction : 该商品或已下架 params = " + params);
                AuctionResponseModel responseModel = new AuctionResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_NOT_FOUND);
                responseModel.setMessage("该商品或已下架");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            } else {
                logger.info("auction : 转链成功 params = " + params);
                AuctionResponseModel responseModel = new AuctionResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_SUCCESS);
                responseModel.setAuction(aliConvertEntity);
                responseModel.setMessage("转链成功");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("auction : 发生异常 params = " + params + " 异常信息：" + e.getMessage());
            AuctionResponseModel responseModel = new AuctionResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_SERVER_ERROR);
            responseModel.setMessage("发生错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
        }
    }

    @Override
    @Before({AppInterceptor.class, TokenInterceptor.class})
    public void brands() {
        String params = this.getAttr("p");
        BrandListRequestModel requestModel = JSON.parseObject(params, BrandListRequestModel.class);
        if (requestModel == null || !requestModel.isBrandListRequestParamsOk()) {
            logger.info("brands : 参数错误 params = " + params);
            BrandListResponseModel responseModel = new BrandListResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_BAD_PARAMS);
            responseModel.setMessage("参数错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            return;
        }

        try {
            List<GuideEntity> brandList = BrandServices.getBrandList(requestModel);
            if (brandList == null) {
                logger.info("brands : 查询到的数据集是NULL params = " + params);
                BrandListResponseModel responseModel = new BrandListResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_SERVER_ERROR);
                responseModel.setMessage("发生错误，请重试");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
                return;
            }
            if (brandList.size() < 1) {
                logger.info("brands : 查询到的数据集的数量为0 params = " + params);
                BrandListResponseModel responseModel = new BrandListResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_NOT_FOUND);
                responseModel.setMessage("没有找到，请新建");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
                return;
            }
            logger.info("brands : 查询推广位成功 数量为" + brandList.size() + " params = " + params);
            BrandListResponseModel responseModel = new BrandListResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_SUCCESS);
            responseModel.setBrands(brandList);
            responseModel.setMessage("获取成功");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("brands : 发生异常 params = " + params + " 异常信息为：" + e.getMessage());
            BrandListResponseModel responseModel = new BrandListResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_SERVER_ERROR);
            responseModel.setMessage("发生错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
        }
    }

    @Override
    @Before({AppInterceptor.class, TokenInterceptor.class})
    public void createBrands() {
        String params = this.getAttr("p");
        BrandCreateRequestModel requestModel = JSON.parseObject(params, BrandCreateRequestModel.class);
        if (requestModel == null || !requestModel.isBrandCreateRequestParamsOk()) {
            logger.info("createBrands : 参数错误 params = " + params);
            BrandCreateResponseModel responseModel = new BrandCreateResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_BAD_PARAMS);
            responseModel.setMessage("参数错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
            return;
        }

        try {
            List<GuideEntity> brandList = BrandServices.createBrand(requestModel);
            if (brandList == null || brandList.size() < 1) {
                logger.info("createBrands : 创建结果是NULL params = " + params);
                BrandCreateResponseModel responseModel = new BrandCreateResponseModel(requestModel);
                responseModel.setCode(ResponseModel.RC_SERVER_ERROR);
                responseModel.setMessage("创建失败，请重试");
                renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
                return;
            }
            logger.info("createBrands : 创建成功 数量为" + brandList.size() + " params = " + params);
            BrandCreateResponseModel responseModel = new BrandCreateResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_SUCCESS);
            responseModel.setBrands(brandList);
            responseModel.setMessage("创建成功");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("createBrands : 发生异常 params = " + params + " 异常信息为：" + e.getMessage());
            BrandCreateResponseModel responseModel = new BrandCreateResponseModel(requestModel);
            responseModel.setCode(ResponseModel.RC_SERVER_ERROR);
            responseModel.setMessage("发生错误，请重试");
            renderJson(JSON.toJSONString(responseModel, new SerializeFilter[]{GoodsRequestModel.propertyFilter, TokenModel.propertyFilter, TokenModel.valueFilter}, SerializerFeature.WriteMapNullValue));
        }
    }

}
