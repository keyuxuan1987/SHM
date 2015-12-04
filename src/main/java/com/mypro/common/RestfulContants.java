package com.mypro.common;
/*
GET，用来浏览(browse)资源
POST，用来新建(create)资源
PUT，用来更新(update)资源
DELETE，用来删除(delete)资源

http://IP:端口/piba/版本/对象/方法
如：
http://localhost:8080/piba
	用户模块：
	URL								HTTP	功能
	/1.0/classes/add				POST	添加对象
	/1.0/classes/delete/objectId	DELETE	删除对象
	/1.0/classes/findById/objectId	GET		根据ID查询
	/1.0/classes/put				PUT		更新对象
	/1.0/classes/getList			GET		获取所有对象
*/
public class RestfulContants {
	
	/** restful 用户 模块常量*/
	public static final String USER_REQUEST_MAPPING = "/1.0/users/";
	
	/** restful 订单模块常量*/
	public static final String ORDER_REQUEST_MAPPING = "/1.0/orders/";
	
	/** 添加 数据 */
	public static final String ADD = "add";

	/** 删除数据 */
	public static final String DELETE = "delete";

	/** 根据ID查找 数据 */
	public static final String FINDBYID = "findById";

	/** 修改 数据 */
	public static final String PUT = "put";

	/** 获取列表 数据 */
	public static final String GETLIST = "getList";
	
	public static final String TICKETID = "ticketId";
	
}
