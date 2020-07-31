package com.mdy.dzs.game.ao;

import java.util.ArrayList;
import java.util.List;

import com.mdy.dzs.game.domain.friend.RoleFriend;
import com.mdy.dzs.game.domain.role.Role;
import com.mdy.dzs.game.domain.union.MemberVO;
import com.mdy.dzs.game.domain.union.RoleUnion;
import com.mdy.dzs.game.domain.union.UnionApply;

public class UnionApplyAO extends BaseAO {

     /**
      * 创建用户申请信息
      */
     public void createUnionApply(int unionId, int roleId, int level, int rank) {

          unionApplyDAO().createUnionApply(UnionApply.valueOf(unionId, roleId, level, rank));
     }

     /**
      * 查询用户申请的数量
      */
     public List<UnionApply> queryRoleApply(int roleId) {

          return unionApplyDAO().queryRoleApplyList(roleId);
     }

     /**
      * 申请该帮派的人数
      */
     public int queryApplyNum(int unionId) {

          return unionApplyDAO().queryApplyList(unionId).size();
     }

     /**
      * 删除玩家申请
      * 
      * @param roleId
      * @param unionId
      */
     public void deleteRoleApply(int roleId, int unionId) {
          unionApplyDAO().deleteRoleApply(roleId, unionId);

     }

     /**
      * 查询帮派申请列表
      * 
      * @param unionId
      * @return
      */
     public List<UnionApply> queryApplyList(int unionId) {
          return unionApplyDAO().queryApplyList(unionId);
     }

     /**
      * 构造返回的申请列表
      * 
      * @param unionId
      * @param type
      * @return
      */
     public List<MemberVO> createRetrunList(int unionId, int type) {
          List<MemberVO> appList = new ArrayList<MemberVO>();
          List<UnionApply> alist = unionApplyDAO().queryApplyList(unionId);
          for (UnionApply unionApply : alist) {
               Role role = roleDAO().queryById(unionApply.getRoleId());
               appList.add(MemberVO.valueOf(role, unionApply));
          }
          return appList;
     }

     /**
      * 构造返回成员列表
      * 
      * @param unionId
      * @param type
      * @return
      */
     public List<MemberVO> createMemberList(int unionId, int roleId) {
          List<MemberVO> appList = new ArrayList<MemberVO>();
          List<RoleUnion> mlist = roleUnionDAO().queryAllMember(unionId);
          for (RoleUnion roleUnion : mlist) {
               Role role = roleDAO().queryById(roleUnion.getRoleId());
               RoleFriend roleFriend = roleFriendDAO().queryOneFriend(roleId, roleUnion.getRoleId());
               // 0:是好友，1：不是好友，2：已申请
               int isFriend = 1;
               if (roleFriend != null) {
                    switch (roleFriend.getStatus()) {
                    case 1:
                         isFriend = 2;
                         break;
                    case 2:
                         isFriend = 0;
                         break;
                    }
               }
               appList.add(MemberVO.memberValueOf(role, roleUnion, isFriend));
          }
          return appList;
     }

     /**
      * 查询某一条申请信息
      */
     public UnionApply queryOneUnionApply(int roleId, int unionId) {

          return unionApplyDAO().queryApply(roleId, unionId);
     }
}
