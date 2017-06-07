package com.yaoli.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SendUpdateSewageIdTask extends ChannelInboundHandlerAdapter {
	private byte[] req = new byte[3];
	
	public SendUpdateSewageIdTask(){
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		req[0] = (byte)35;
		req[1] = (byte)1;
		req[2] = (byte)1;
		ctx.writeAndFlush(req);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
        System.out.println("client exceptionCaught..");
        ctx.close();
	}
}
