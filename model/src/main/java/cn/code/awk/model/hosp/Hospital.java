package cn.code.awk.model.hosp;

import cn.code.awk.model.base.BaseMongoEntity;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * Hospital
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Hospital")
@Document("Hospital")
public class Hospital extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "医院编号", example = "1001")
    @Indexed(unique = true) //唯一索引
    private String hoscode;

    @ApiModelProperty(value = "医院名称", example = "XXX医院")
    @Indexed //普通索引
    private String hosname;

    @ApiModelProperty(value = "医院类型", example = "XXX儿童医院")
    private String hostype;

    @ApiModelProperty(value = "省code", example = "陕西省")
    private String provinceCode;

    @ApiModelProperty(value = "市code", example = "001")
    private String cityCode;

    @ApiModelProperty(value = "区code", example = "002")
    private String districtCode;

    @ApiModelProperty(value = "详情地址", example = "XXX地址")
    private String address;

    @ApiModelProperty(value = "医院logo", example = "logo图URL")
    private String logoData;

    @ApiModelProperty(value = "医院简介", example = "introduction")
    private String intro;

    @ApiModelProperty(value = "坐车路线",example = "线路")
    private String route;

    @ApiModelProperty(value = "状态 0：未上线 1：已上线", example = "0")
    private Integer status;

    //预约规则
    @ApiModelProperty(value = "预约规则", example = "预约规则")
    private BookingRule bookingRule;

    public void setBookingRule(String bookingRule) {
        this.bookingRule = JSONObject.parseObject(bookingRule, BookingRule.class);
    }

}