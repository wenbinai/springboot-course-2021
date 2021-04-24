package com.example.mybatisexamples.example06;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisexamples.entity.GithubUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface GithubUserMapper06 extends BaseMapper<GithubUser> {

    /**
     * 方法实现查询，大于等于，stars/repos/followers；在指定时间以前创建的，指定性别的，用户
     * @param optionals
     * @return
     */
    default List<GithubUser> listByOptionals(QueryOptional optionals) {
        LambdaQueryWrapper<GithubUser> qw = new LambdaQueryWrapper<>();
        // 不成立则不会拼接查询条件
        if(optionals.getStars() != null) {
            qw.ge(GithubUser::getStars, optionals.getStars());
        }
        if(optionals.getRepos() != null) {
            qw.ge(GithubUser::getRepos, optionals.getRepos());
        }
        if(optionals.getBeforeCreateTime() != null) {
            qw.lt(GithubUser::getCreateTime, optionals.getBeforeCreateTime());
        }
        if (optionals.getFollowers() != null) {
            qw.ge(GithubUser::getFollowers, optionals.getFollowers());
        }
        // 3参数方法，可不写if判断
        qw.eq(optionals.getGender() != null,
                GithubUser::getGender,
                optionals.getGender());
        return selectList(qw);
    }
    List<GithubUser> listByOptionals2(QueryOptional optionals);

}