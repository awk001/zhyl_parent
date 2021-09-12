package cn.code.awk.exception;

import cn.code.awk.result.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义全局异常类
 *
 * @author qy
 */
@Data
@ApiModel(value = "自定义全局异常类")
public class ZhylException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param message
     * @param code
     */
    public ZhylException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ZhylException(String message) {
        super(message);
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum
     */
    public ZhylException(R resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ZhylException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}