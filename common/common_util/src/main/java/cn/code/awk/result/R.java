package cn.code.awk.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class R<T> {

    @ApiModelProperty(value = "返回码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "返回消息", example = "message")
    private String message;

    @ApiModelProperty(value = "返回数据", example = "json data")
    private T data;

    public R() {
    }

    private static <T> R<T> build(T data) {
        R<T> R = new R<T>();
        if (data != null)
            R.setData(data);
        return R;
    }

    private static <T> R<T> build(T body, RCodeEnum RCodeEnum) {
        R<T> R = build(body);
        R.setCode(RCodeEnum.getCode());
        R.setMessage(RCodeEnum.getMessage());
        return R;
    }

    private static <T> R<T> build(Integer code, String message) {
        R<T> R = build(null);
        R.setCode(code);
        R.setMessage(message);
        return R;
    }

    public static <T> R<T> ok() {
        return R.ok(null);
    }

    /**
     * 操作成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> ok(T data) {
        R<T> R = build(data);
        return build(data, RCodeEnum.SUCCESS);
    }

    public static <T> R<T> fail() {
        return R.fail(null);
    }

    /**
     * 操作失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(T data) {
        R<T> R = build(data);
        return build(data, RCodeEnum.FAIL);
    }

    public R<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        return this.getCode().intValue() == RCodeEnum.SUCCESS.getCode().intValue();
    }
}