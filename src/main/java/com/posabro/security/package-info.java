/**
 * in order to use this component you have to declare the filter Servlet in the web.xml file
 * 
 *  <filter>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 *  </filter>
 *  
 *  <filter-mapping>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <url-pattern>/*</url-pattern>
 *  </filter-mapping>
 * 
 * As well as the import of the security context in the context file
 * 
 * <import resource="security-context.xml"/>
 * 
 * To define a dynamic menu you can take advantage of the Spring Security tags, here you have an example
 * 
 * <c:if test="${loggedIn}">
 *          <ul id="menu" class="ui-side-menu">
 *              <li class="ui-state-disabled"><a href="#"><spring:message code="menu.catalogs"/></a></li>
 *              <li><a href="#"><spring:message code="menu.processes"/></a></li>
 *              <sec:authorize access="hasRole('ROLE_ADMIN')">
 *                  <li><a href="#"><spring:message code="menu.admin"/></a></li>
 *                  <li>
 *                      <a href="#"><spring:message code="menu.security"/></a>
 *                      <ul class="ui-side-menu">
 *                          <li><a href="<%=request.getContextPath()%>/users"><spring:message code="menu.security.users"/></a></li>
 *                          <li><a href="<%=request.getContextPath()%>/roles"><spring:message code="menu.security.roles"/></a></li>
 *                          <li><a href="<%=request.getContextPath()%>/groups"><spring:message code="menu.security.groups"/></a></li>
 *                      </ul>
 *                  </li>
 *              </sec:authorize>
 *          </ul>
 * </c:if>
 * 
 */
package com.posabro.security;
