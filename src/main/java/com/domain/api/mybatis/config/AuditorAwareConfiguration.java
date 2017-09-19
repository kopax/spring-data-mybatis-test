package com.domain.api.mybatis.config;

//
///**
// * Created by dka on 4/9/17.
// */
//
//@Configuration
//public class AuditorAwareConfiguration {
//
//	@Bean
//	public AuditorAware<Long> auditorAware() {
//		return new AuditorAware<Long>() {
//			@Override
//			public Long getCurrentAuditor() {
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//				if (authentication == null || !authentication.isAuthenticated()) {
//					return null;
//				}
//
//				User user = null;
//				Object principal = authentication.getPrincipal();
//
//				if (principal.getClass().equals(User.class)) {
//					user = (User) principal;
//				}
//
//				if (null == user) {
//					return null;
//				}
//
//				return user.getId();
//			}
//		};
//	}
//}
