package com.mall.member.dao;

import com.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 19:29:06
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
