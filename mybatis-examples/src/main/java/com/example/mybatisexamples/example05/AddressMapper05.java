package com.example.mybatisexamples.example05;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisexamples.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AddressMapper05 extends BaseMapper<Address> {
    @Select("select * from address a where a.user_id=#{uid}")
    List<Address> listAddresses(long uid);

    List<AddressDTO05> listAddressDTOs(@Param("detail") String detail);
}