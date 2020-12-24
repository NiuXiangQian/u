package com.github.niu.u.common.enums;

/**
 * @description: 状态吗设置
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/21 7:46 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/21 7:46 下午
 * @updateRemark:
 * @version: 1.0
 **/
public enum ResultCode {
	/* 通用状态码 成功与失败 */
	SUCCESS(1,"ok"), FAILED(-1,"操作失败"),
	/* 参数错误  101 - 199*/
	PARAM_IS_INVALID(101,"参数无效"),
	PARAM_IS_BLANK(101,"参数为空"),
	/* 用户错误  201 - 299  */
	USER_NOT_LOGIN(201,"未登录"),
	USER_NOT_EXIST(202,"用户不存在"),
	USER_LOGIN_ERROR(203,"登陆失败，账号或者密码有误"),
	NOT_PERMISSION(204,"无权限访问"),
	/* 业务错误 301 - 399*/
	DATA_NOT_FOUND(301,"没有数据"),
	//.......更多
	;
	private Integer code;
	private String msg;


	ResultCode(Integer code, String msg) {
		this.code=code;
		this.msg=msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg(){
		return msg;
	}
}
