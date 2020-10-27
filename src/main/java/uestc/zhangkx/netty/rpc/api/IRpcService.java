package uestc.zhangkx.netty.rpc.api;

/**
 * API:主要是定义对外开放的功能和服务接口
 */
public interface IRpcService {

	/** 加 */
	public int add(int a,int b);

	/** 减 */
	public int sub(int a,int b);

	/** 乘 */
	public int mult(int a,int b);

	/** 除 */
	public int div(int a,int b);

}
