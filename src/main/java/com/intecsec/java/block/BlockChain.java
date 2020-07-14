package com.intecsec.java.block;

import com.intecsec.java.util.HttpUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter.Peng
 * @date 2018/3/15
 */
public class BlockChain {
    //用来存储区块
    private List<Block> lBlockchain=new ArrayList<>();


    public BlockChain(){

    }


    //创建新块
    public Block NewBlock(){
        Block bRet=null;

        //在这里创建一个新块

        return bRet;
    }

    //创建新块
    public Block NewBlock(int i, String proof, String hash, Timestamp c, String sender, String recipient){
        Block bRet=null;

        //在这里创建一个新块
        bRet = new Block(i,proof,hash,c,sender,recipient);

        return bRet;
    }

    //Hash 一个块
    public static String Hash(Block block){
        String sHash=null;

        //在这里Hash 一个块
        String s=block.sPreviousHash+block.sProof+block.sRecipient+block.sSender+block.tsCreateTime.toString();

        sHash = BlockUtil.MD5(s);

        return sHash;
    }

    public static boolean ValidProof(String pre,String cur){

        //验证这个成语的头一个字是不是上一个成语的最后一个字
        if(cur.charAt(0)!=pre.charAt(pre.length()-1)){
            return false;
        }

        //验证是否是成语
        //http://chengyu.t086.com/chaxun.php?q=%B9%E2%C3%F7%D5%FD%B4%F3&t=ChengYu
        Map<String, String> params = new HashMap<>();
        params.put("q", cur);
        params.put("t", "ChengYu");
        String content= HttpUtil.get("http://chengyu.t086.com/chaxun.php", params);
        if(content.indexOf("没有找到与您搜索相关的成语")!=-1 || content.indexOf("搜索词太长")!=-1){
            return false;
        }

        return true;
    }

}
