/*
Navicat MySQL Data Transfer

Source Server         : link_admin
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : link_admin

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-10-12 15:39:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dept`;
CREATE TABLE `t_sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `levels` int(11) DEFAULT NULL,
  `for_service` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` VALUES ('2', '上海集团', '0', '0', null, '0', '1');
INSERT INTO `t_sys_dept` VALUES ('4', '青浦分公司', '2', '1', null, '0', '12');
INSERT INTO `t_sys_dept` VALUES ('6', '人事部', '4', '2', null, '0', '11');
INSERT INTO `t_sys_dept` VALUES ('8', '信息中心', '4', '2', null, '0', '13');
INSERT INTO `t_sys_dept` VALUES ('10', '安全部', '4', '2', null, '0', '15');
INSERT INTO `t_sys_dept` VALUES ('11', 'IT与流程', '5', '2', null, '0', '21');
INSERT INTO `t_sys_dept` VALUES ('12', '快递系统研发部', '11', '2', null, '0', '12');
INSERT INTO `t_sys_dept` VALUES ('13', '渠道系统研发部', '11', '2', null, '0', '13');
INSERT INTO `t_sys_dept` VALUES ('14', '整车业务研发部', '11', '2', null, '0', '13');
INSERT INTO `t_sys_dept` VALUES ('15', '物流系统研发部', '11', '2', null, '0', '14');
INSERT INTO `t_sys_dept` VALUES ('16', '徐汇分公司', '2', '1', null, '0', '2');
INSERT INTO `t_sys_dept` VALUES ('17', '研发部', '16', null, null, '0', '21');
INSERT INTO `t_sys_dept` VALUES ('18', '快递系统研发部', '4', null, null, '0', '1');
INSERT INTO `t_sys_dept` VALUES ('20', '流程中心', '4', null, null, '0', null);
INSERT INTO `t_sys_dept` VALUES ('21', '渠道系统研发部', '22', null, null, '0', '1');
INSERT INTO `t_sys_dept` VALUES ('22', '西安分公司', '2', null, null, '0', null);
INSERT INTO `t_sys_dept` VALUES ('25', '程序员鼓励师', '16', null, null, '0', null);
INSERT INTO `t_sys_dept` VALUES ('28', '财务部', '16', null, null, '0', null);
INSERT INTO `t_sys_dept` VALUES ('29', '人事部', '16', null, null, '0', null);

-- ----------------------------
-- Table structure for `t_sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_type` varchar(50) NOT NULL DEFAULT '0' COMMENT ' 父ID ',
  `data_key` varchar(50) NOT NULL COMMENT ' 数据编码 ',
  `data_value` varchar(200) NOT NULL COMMENT ' 数据名称/值 ',
  `sorts` int(11) unsigned DEFAULT '1' COMMENT ' 顺序 ',
  `description` varchar(400) DEFAULT NULL COMMENT '数据描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` VALUES ('2', 'data_scope', '1', '全部数据权限', '1', '');
INSERT INTO `t_sys_dict` VALUES ('3', 'data_scope', '2', '自定义数据权限', '2', '');
INSERT INTO `t_sys_dict` VALUES ('4', 'data_scope', '3', '本部门及以下数据权限', '3', '');
INSERT INTO `t_sys_dict` VALUES ('5', 'data_scope', '4', '本部门数据权限', '4', '');
INSERT INTO `t_sys_dict` VALUES ('6', 'data_scope', '5', '仅本人数据权限', '5', '');

-- ----------------------------
-- Table structure for `t_sys_job`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_job`;
CREATE TABLE `t_sys_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_job
-- ----------------------------
INSERT INTO `t_sys_job` VALUES ('2', '初级软件工程师', '1', '1');
INSERT INTO `t_sys_job` VALUES ('3', '中级软件工程师', '1', '2');
INSERT INTO `t_sys_job` VALUES ('4', '高级软件工程师', '1', '3');
INSERT INTO `t_sys_job` VALUES ('5', '资深软件工程师', '1', '4');
INSERT INTO `t_sys_job` VALUES ('6', '技术专家', '1', '5');

-- ----------------------------
-- Table structure for `t_sys_logs`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_logs`;
CREATE TABLE `t_sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginuser` varchar(30) DEFAULT NULL,
  `vsername` varchar(30) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `request_method` varchar(30) DEFAULT NULL,
  `content_type` varchar(50) DEFAULT NULL,
  `request_params` varchar(2000) DEFAULT NULL,
  `response_result` varchar(2000) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `types` int(11) DEFAULT NULL,
  `i_frame` int(11) DEFAULT NULL COMMENT '是否外链',
  `url` varchar(150) DEFAULT NULL,
  `levels` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sorts` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `component_name` varchar(50) DEFAULT NULL,
  `component_path` varchar(150) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `cache` int(11) DEFAULT NULL,
  `hidden` int(11) DEFAULT NULL,
  `permission_flag` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES ('4', '外链', '0', '1', '1', 'https://github.com/252956/vue-link-admin', null, null, '202', null, null, null, 'link', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('5', '错误页面', '0', '0', '0', '/error', null, null, '199', null, '', '', '404', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('6', '401', '5', '1', '0', '/error/401', null, null, '30', null, 'Page401', '/error-page/401', null, '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('7', '404', '5', '1', '0', '/error/404', null, null, '31', null, 'Page404', '/error-page/404', null, '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('8', '系统权限', '0', '0', '0', '/permission', null, null, '100', null, '', '', 'lock', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('9', '用户管理', '8', '1', '0', '/permission/user', null, null, '1', null, 'User', '/permission/user', '', '0', '0', 'user:list');
INSERT INTO `t_sys_permission` VALUES ('10', '角色管理', '8', '1', '0', '/permission/role', null, null, '2', null, 'Role', '/permission/role', null, '0', '0', 'role:list');
INSERT INTO `t_sys_permission` VALUES ('11', '权限管理', '8', '1', '0', '/permission/permission', null, null, '3', null, 'Permission', '/permission/permission', null, '0', '0', 'permission:list');
INSERT INTO `t_sys_permission` VALUES ('13', '部门管理', '8', '1', '0', '/permission/dept', null, null, '4', null, 'Department', '/permission/department', null, '0', '0', 'dept:list');
INSERT INTO `t_sys_permission` VALUES ('14', '图标', '0', '1', '0', '/icon', null, null, '201', null, 'Icons', '/icons/index', 'icon', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('17', '新增用户', '9', '2', null, '/rest/user/add', null, null, '201', null, null, null, null, null, null, 'user:add');
INSERT INTO `t_sys_permission` VALUES ('18', '编辑用户', '9', '2', null, '/rest/user/update', null, null, '202', null, null, null, null, null, null, 'user:edit');
INSERT INTO `t_sys_permission` VALUES ('19', '删除用户', '9', '2', null, '/rest/user/delete', null, null, '203', null, null, null, null, null, null, 'user:del');
INSERT INTO `t_sys_permission` VALUES ('21', '新增角色', '10', '2', null, '/rest/role/add', null, null, '211', null, null, null, null, null, null, 'role:add');
INSERT INTO `t_sys_permission` VALUES ('22', '编辑角色', '10', '2', null, '/rest/role/update', null, null, '212', null, null, null, null, null, null, 'role:edit');
INSERT INTO `t_sys_permission` VALUES ('23', '删除角色', '10', '2', null, '/rest/role/delete', null, null, '213', null, null, null, null, null, null, 'role:del');
INSERT INTO `t_sys_permission` VALUES ('25', '新增权限', '11', '2', null, '/rest/permission/add', null, null, '221', null, null, null, null, null, null, 'permission:add');
INSERT INTO `t_sys_permission` VALUES ('26', '编辑权限', '11', '2', null, '/rest/permission/update', null, null, '222', null, null, null, null, null, null, 'permission:edit');
INSERT INTO `t_sys_permission` VALUES ('27', '删除权限', '11', '2', null, '/rest/permission/delete', null, null, '223', null, null, null, null, null, null, 'permission:del');
INSERT INTO `t_sys_permission` VALUES ('29', '新增部门', '13', '2', null, '/rest/department/add', null, null, '241', null, null, null, null, null, null, 'dept:add');
INSERT INTO `t_sys_permission` VALUES ('30', '编辑部门', '13', '2', null, '/rest/department/update', null, null, '242', null, null, null, null, null, null, 'dept:edit');
INSERT INTO `t_sys_permission` VALUES ('31', '删除部门', '13', '2', null, '/rest/department/delete', null, null, '243', null, null, null, null, null, null, 'dept:del');
INSERT INTO `t_sys_permission` VALUES ('32', '系统监控', '0', '0', '0', '/monitor', null, null, '101', null, '', '', 'monitor', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('33', '错误日志', '32', '1', '0', '/monitor/error-log', null, null, '40', null, 'ErrorLog', '/monitor/error-log', null, '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('34', '业务日志', '32', '1', '0', '/monitor/blog', null, null, '41', null, 'Blog', '/monitor/blog', null, '0', '0', 'blog:list');
INSERT INTO `t_sys_permission` VALUES ('41', '数据字典', '8', '1', '0', '/permission/dict', null, null, '6', null, 'Dict', '/permission/dict', null, '0', '0', 'dict:list');
INSERT INTO `t_sys_permission` VALUES ('43', '数据权限', '10', '2', null, '/rest/role/saveDataScope', null, null, '1', null, null, null, null, null, null, 'role:datascope');
INSERT INTO `t_sys_permission` VALUES ('44', '新增字典', '41', '2', null, '/rest/dict/add', null, null, '1', null, null, null, null, null, null, 'dict:add');
INSERT INTO `t_sys_permission` VALUES ('45', '编辑字典', '41', '2', null, '/rest/dict/update', null, null, '2', null, null, null, null, null, null, 'dict:edit');
INSERT INTO `t_sys_permission` VALUES ('46', '删除字典', '41', '2', null, '/rest/dict/delete', null, null, '3', null, null, null, null, null, null, 'dict:del');
INSERT INTO `t_sys_permission` VALUES ('49', '岗位管理', '8', '1', '0', '/permission/job', null, null, '5', null, 'Job', '/permission/job', '', '0', '0', 'job:list');
INSERT INTO `t_sys_permission` VALUES ('50', '添加岗位', '49', '2', '0', '/rest/job/add', null, null, '1', null, '', '', '', '0', '0', 'job:add');
INSERT INTO `t_sys_permission` VALUES ('51', '编辑岗位', '49', '2', '0', '/rest/job/update', null, null, '2', null, '', '', '', '0', '0', 'job:edit');
INSERT INTO `t_sys_permission` VALUES ('52', '删除岗位', '49', '2', '0', '/rest/job/delete', null, null, '3', null, '', '', '', '0', '0', 'job:del');
INSERT INTO `t_sys_permission` VALUES ('54', '组件', '0', '0', '0', '/component', null, null, '103', null, '', '', 'component', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('55', 'pdf', '54', '1', '0', '/component/pdf', null, null, '6', null, 'Pdf', '/component/pdf', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('56', 'excel', '54', '0', '0', '/excel', null, null, '7', null, '', '', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('57', 'zip', '54', '1', '0', '/component/zip', null, null, '8', null, 'Zip', '/component/zip', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('58', '打赏', '0', '1', '1', 'https://252956.github.io/donate/', null, null, '203', null, '', '', 'donate', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('59', 'upload', '54', '1', '0', '/component/upload', null, null, '4', null, 'Upload', '/component/upload', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('62', 'Markdown', '54', '1', '0', '/component/markdown', null, null, '2', null, 'MarkdownDemo', '/component/markdown', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('63', 'JSON 编辑器', '54', '1', '0', '/component/json-editor', null, null, '3', null, 'JsonEditorDemo', '/component/json-editor', '', '1', '0', null);
INSERT INTO `t_sys_permission` VALUES ('64', '键盘图表', '54', '1', '0', '/component/keyboard', null, null, '11', null, 'KeyboardChart', '/component/keyboard', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('65', '折线图', '54', '1', '0', '/component/line', null, null, '12', null, 'LineChart', '/component/line', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('66', '混合图表', '54', '1', '0', '/component/mix-chart', null, null, '13', null, 'MixChart', '/component/mix-chart', '', '0', '0', null);
INSERT INTO `t_sys_permission` VALUES ('67', '导出 Excel', '56', '1', '0', '/component/excel/export-excel', null, null, '1', null, 'ExportExcel', '/component/excel/export-excel', '', '1', '0', null);
INSERT INTO `t_sys_permission` VALUES ('68', '导出 已选择项', '56', '1', '0', '/component/excel/select-excel', null, null, '2', null, 'SelectExcel', '/component/excel/select-excel', '', '1', '0', null);
INSERT INTO `t_sys_permission` VALUES ('69', '导出 多级表头', '56', '1', '0', '/component/excel/merge-header', null, null, '3', null, 'MergeHeader', '/component/excel/merge-header', '', '1', '0', null);
INSERT INTO `t_sys_permission` VALUES ('70', '城市控件', '54', '1', '0', '/component/citycontrol', null, null, '1', null, 'CityControlDemo', '/component/citycontrol', '', '0', '0', '');

-- ----------------------------
-- Table structure for `t_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `data_scope` varchar(11) DEFAULT NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门及以下数据权限4：本部门数据权限 5：本人）',
  `levels` int(11) DEFAULT NULL COMMENT '新增用户时只能赋予比自己级别低的角色',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', 'admin', '1', '1', null, '超级管理员');
INSERT INTO `t_sys_role` VALUES ('2', 'editor', '1', '2', null, '系统演示角色');
INSERT INTO `t_sys_role` VALUES ('11', 'test', '1', '5', null, '测试角色111');

-- ----------------------------
-- Table structure for `t_sys_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_dept`;
CREATE TABLE `t_sys_role_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_sys_role_dept
-- ----------------------------
INSERT INTO `t_sys_role_dept` VALUES ('12', '2', '10');

-- ----------------------------
-- Table structure for `t_sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2175 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------
INSERT INTO `t_sys_role_permission` VALUES ('2063', '11', '14');
INSERT INTO `t_sys_role_permission` VALUES ('2064', '11', '4');
INSERT INTO `t_sys_role_permission` VALUES ('2065', '11', '58');
INSERT INTO `t_sys_role_permission` VALUES ('2106', '1', '8');
INSERT INTO `t_sys_role_permission` VALUES ('2107', '1', '9');
INSERT INTO `t_sys_role_permission` VALUES ('2108', '1', '17');
INSERT INTO `t_sys_role_permission` VALUES ('2109', '1', '18');
INSERT INTO `t_sys_role_permission` VALUES ('2110', '1', '19');
INSERT INTO `t_sys_role_permission` VALUES ('2111', '1', '10');
INSERT INTO `t_sys_role_permission` VALUES ('2112', '1', '43');
INSERT INTO `t_sys_role_permission` VALUES ('2113', '1', '21');
INSERT INTO `t_sys_role_permission` VALUES ('2114', '1', '22');
INSERT INTO `t_sys_role_permission` VALUES ('2115', '1', '23');
INSERT INTO `t_sys_role_permission` VALUES ('2116', '1', '11');
INSERT INTO `t_sys_role_permission` VALUES ('2117', '1', '25');
INSERT INTO `t_sys_role_permission` VALUES ('2118', '1', '26');
INSERT INTO `t_sys_role_permission` VALUES ('2119', '1', '27');
INSERT INTO `t_sys_role_permission` VALUES ('2120', '1', '13');
INSERT INTO `t_sys_role_permission` VALUES ('2121', '1', '29');
INSERT INTO `t_sys_role_permission` VALUES ('2122', '1', '30');
INSERT INTO `t_sys_role_permission` VALUES ('2123', '1', '31');
INSERT INTO `t_sys_role_permission` VALUES ('2124', '1', '49');
INSERT INTO `t_sys_role_permission` VALUES ('2125', '1', '50');
INSERT INTO `t_sys_role_permission` VALUES ('2126', '1', '51');
INSERT INTO `t_sys_role_permission` VALUES ('2127', '1', '52');
INSERT INTO `t_sys_role_permission` VALUES ('2128', '1', '41');
INSERT INTO `t_sys_role_permission` VALUES ('2129', '1', '44');
INSERT INTO `t_sys_role_permission` VALUES ('2130', '1', '45');
INSERT INTO `t_sys_role_permission` VALUES ('2131', '1', '46');
INSERT INTO `t_sys_role_permission` VALUES ('2132', '1', '32');
INSERT INTO `t_sys_role_permission` VALUES ('2133', '1', '33');
INSERT INTO `t_sys_role_permission` VALUES ('2134', '1', '34');
INSERT INTO `t_sys_role_permission` VALUES ('2135', '1', '54');
INSERT INTO `t_sys_role_permission` VALUES ('2136', '1', '70');
INSERT INTO `t_sys_role_permission` VALUES ('2137', '1', '62');
INSERT INTO `t_sys_role_permission` VALUES ('2138', '1', '63');
INSERT INTO `t_sys_role_permission` VALUES ('2139', '1', '59');
INSERT INTO `t_sys_role_permission` VALUES ('2140', '1', '55');
INSERT INTO `t_sys_role_permission` VALUES ('2141', '1', '56');
INSERT INTO `t_sys_role_permission` VALUES ('2142', '1', '67');
INSERT INTO `t_sys_role_permission` VALUES ('2143', '1', '68');
INSERT INTO `t_sys_role_permission` VALUES ('2144', '1', '69');
INSERT INTO `t_sys_role_permission` VALUES ('2145', '1', '57');
INSERT INTO `t_sys_role_permission` VALUES ('2146', '1', '64');
INSERT INTO `t_sys_role_permission` VALUES ('2147', '1', '65');
INSERT INTO `t_sys_role_permission` VALUES ('2148', '1', '66');
INSERT INTO `t_sys_role_permission` VALUES ('2149', '1', '5');
INSERT INTO `t_sys_role_permission` VALUES ('2150', '1', '6');
INSERT INTO `t_sys_role_permission` VALUES ('2151', '1', '7');
INSERT INTO `t_sys_role_permission` VALUES ('2152', '1', '14');
INSERT INTO `t_sys_role_permission` VALUES ('2153', '1', '4');
INSERT INTO `t_sys_role_permission` VALUES ('2154', '1', '58');
INSERT INTO `t_sys_role_permission` VALUES ('2155', '2', '54');
INSERT INTO `t_sys_role_permission` VALUES ('2156', '2', '70');
INSERT INTO `t_sys_role_permission` VALUES ('2157', '2', '62');
INSERT INTO `t_sys_role_permission` VALUES ('2158', '2', '63');
INSERT INTO `t_sys_role_permission` VALUES ('2159', '2', '59');
INSERT INTO `t_sys_role_permission` VALUES ('2160', '2', '55');
INSERT INTO `t_sys_role_permission` VALUES ('2161', '2', '56');
INSERT INTO `t_sys_role_permission` VALUES ('2162', '2', '67');
INSERT INTO `t_sys_role_permission` VALUES ('2163', '2', '68');
INSERT INTO `t_sys_role_permission` VALUES ('2164', '2', '69');
INSERT INTO `t_sys_role_permission` VALUES ('2165', '2', '57');
INSERT INTO `t_sys_role_permission` VALUES ('2166', '2', '64');
INSERT INTO `t_sys_role_permission` VALUES ('2167', '2', '65');
INSERT INTO `t_sys_role_permission` VALUES ('2168', '2', '66');
INSERT INTO `t_sys_role_permission` VALUES ('2169', '2', '5');
INSERT INTO `t_sys_role_permission` VALUES ('2170', '2', '6');
INSERT INTO `t_sys_role_permission` VALUES ('2171', '2', '7');
INSERT INTO `t_sys_role_permission` VALUES ('2172', '2', '14');
INSERT INTO `t_sys_role_permission` VALUES ('2173', '2', '4');
INSERT INTO `t_sys_role_permission` VALUES ('2174', '2', '58');

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `uid` varchar(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `vsername` varchar(30) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `jobid` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '管理员', '17601269', '2019-09-07 10:08:25', '1', '4', '6', null, null);
INSERT INTO `t_sys_user` VALUES ('2', 'editor', 'E10ADC3949BA59ABBE56E057F20F883E', '测试员', '666777', '2019-09-09 16:40:43', '1', '16', '5', null, null);
INSERT INTO `t_sys_user` VALUES ('ad904a794a10434b8dec1de8ce23a288', '辉桑', 'E10ADC3949BA59ABBE56E057F20F883E', '辉桑', '1111111', '2019-09-18 13:47:51', '0', '20', '5', null, null);
INSERT INTO `t_sys_user` VALUES ('b88bb916dc054870ae124d92710ac3d3', '云桑1', 'E10ADC3949BA59ABBE56E057F20F883E', '云桑1', '1760126', '2019-09-18 11:11:39', '0', '18', '5', '123', null);
INSERT INTO `t_sys_user` VALUES ('c2bd6773d48643a9ac4540a551ba6ffb', '用嗓', 'E10ADC3949BA59ABBE56E057F20F883E', '用嗓', '222222111', '2019-09-18 15:16:13', '0', '16', '5', null, null);

-- ----------------------------
-- Table structure for `t_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('22', '93bad36e94ab48ab977f9718147c573f', '2');
INSERT INTO `t_sys_user_role` VALUES ('39', 'ab334041dc2c4786a7bddb49bf8b7816', '2');
INSERT INTO `t_sys_user_role` VALUES ('40', 'ab334041dc2c4786a7bddb49bf8b7816', '8');
INSERT INTO `t_sys_user_role` VALUES ('41', 'ab334041dc2c4786a7bddb49bf8b7816', '10');
INSERT INTO `t_sys_user_role` VALUES ('42', 'ab334041dc2c4786a7bddb49bf8b7816', '9');
INSERT INTO `t_sys_user_role` VALUES ('54', '696413831f394ac7b502e828e9ccda3e', '1');
INSERT INTO `t_sys_user_role` VALUES ('55', '6535f71edf3d4e5f9891426bda3de1a1', '1');
INSERT INTO `t_sys_user_role` VALUES ('56', '54136c2416984fa196f970d715e807e1', '1');
INSERT INTO `t_sys_user_role` VALUES ('57', '6b6f0cbdda8d464c90ac58e68a37694e', '2');
INSERT INTO `t_sys_user_role` VALUES ('58', '4b452255b3be4fc6829e6db2922257e1', '2');
INSERT INTO `t_sys_user_role` VALUES ('59', '8dc0a90caeed4310a9c499ef3f1d3c1d', '1');
INSERT INTO `t_sys_user_role` VALUES ('71', 'fa350415ebcc43c2a86cc0cbad98a0a2', '2');
INSERT INTO `t_sys_user_role` VALUES ('80', 'dde9b279949e4e76ac3fff531d712227', '11');
INSERT INTO `t_sys_user_role` VALUES ('82', '2a1d17d2f4184e238816fd8b96195a3e', '2');
INSERT INTO `t_sys_user_role` VALUES ('87', 'b730512de92c4689a87ed0fdd1e2bffc', '2');
INSERT INTO `t_sys_user_role` VALUES ('102', '1', '1');
INSERT INTO `t_sys_user_role` VALUES ('103', '1', '2');
INSERT INTO `t_sys_user_role` VALUES ('106', 'ad904a794a10434b8dec1de8ce23a288', '2');
INSERT INTO `t_sys_user_role` VALUES ('108', 'c2bd6773d48643a9ac4540a551ba6ffb', '2');
INSERT INTO `t_sys_user_role` VALUES ('114', 'b88bb916dc054870ae124d92710ac3d3', '2');
INSERT INTO `t_sys_user_role` VALUES ('116', 'b7ccfe2ef478486f89a944c5a608d90d', '11');
INSERT INTO `t_sys_user_role` VALUES ('117', '2', '2');

-- ----------------------------
-- Table structure for `t_video`
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_video
-- ----------------------------

-- ----------------------------
-- Procedure structure for `p_test`
-- ----------------------------
DROP PROCEDURE IF EXISTS `p_test`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_test`(in cid int)
BEGIN

  select * from t_web_user;

END
;;
DELIMITER ;
