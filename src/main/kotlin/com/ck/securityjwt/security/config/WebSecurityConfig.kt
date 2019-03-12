package com.ck.securityjwt.security.config

import com.ck.securityjwt.security.UserService
import com.ck.securityjwt.security.filters.SecurityBeforeFilter
import com.ck.securityjwt.security.handlers.SecurityAccessDeniedHandler
import com.ck.securityjwt.security.handlers.SecurityFailHandler
import com.ck.securityjwt.security.handlers.SecurityLogoutSuccessHandler
import com.ck.securityjwt.security.handlers.SecuritySuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor

/**
 * Security 安全配置
 *
 * @author QianNianXiaoYao
 * @serial 2019/3/8
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

//    @Autowired
//    private lateinit var requestMatcher: RequestMatcher

    @Autowired
    private lateinit var userService: UserService

    // 配置信息
    @Autowired
    private lateinit var securityConfig: SecurityConfig

    // 无权限
    @Autowired
    private lateinit var securityAccessDeniedHandler: SecurityAccessDeniedHandler

    // 登录失败
    @Autowired
    private lateinit var securityFailHandler: SecurityFailHandler

    // 退出成功之后
    @Autowired
    private lateinit var securityLogoutSuccessHandler: SecurityLogoutSuccessHandler

    // 登录成功之后
    @Autowired
    private lateinit var securitySuccessHandler: SecuritySuccessHandler

    // 每次请求之前过滤器
    @Autowired
    private lateinit var securityBeforeFilter: SecurityBeforeFilter

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     *
     * @author QianNianXiaoYao
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        //注入userDetailsService，需要实现userDetailsService接口
        auth.userDetailsService<UserService>(userService).passwordEncoder(BCryptPasswordEncoder())
    }

    /**
     * 在这里配置哪些页面不需要认证
     *
     * @author QianNianXiaoYao
     */
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/")
    }

    /**
     * 定义安全策略
     *
     * @author QianNianXiaoYao
     */
    override fun configure(http: HttpSecurity) {
        //配置安全策略
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                .withObjectPostProcessor(object : ObjectPostProcessor<FilterSecurityInterceptor> {
//                    override fun <O : FilterSecurityInterceptor> postProcess(o: O): O {
////                        o.securityMetadataSource =
//                        //根据一个url请求，获得访问它所需要的roles权限
//                        // o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
//                        //接收一个用户的信息和访问一个url所需要的权限，判断该用户是否可以访问
//                        // o.setAccessDecisionManager(myAccessDecisionManager);
//                        return o
//                    }
//                })
                .and()
                .formLogin()
                .usernameParameter(securityConfig.usernameParameter)
                .passwordParameter(securityConfig.passwordParameter)
                .loginProcessingUrl(securityConfig.loginUrl)
                .permitAll()
                /**
                 * 登录失败处理
                 */
                .failureHandler(securityFailHandler)
                /**
                 * 登录成功处理
                 */
                .successHandler(securitySuccessHandler)
                .and()
                .logout()
                .logoutUrl(securityConfig.logoutUrl)
                /**
                 * 退出后
                 */
                .logoutSuccessHandler(securityLogoutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling()
                /**
                 * 定义403信息
                 */
                .accessDeniedHandler(securityAccessDeniedHandler)
                .and()
                /**
                 * 请求之前处理
                 */
                .addFilterBefore(securityBeforeFilter, FilterSecurityInterceptor::class.java)

    }
}