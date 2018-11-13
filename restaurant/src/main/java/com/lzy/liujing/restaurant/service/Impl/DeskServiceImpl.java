package com.lzy.liujing.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.liujing.restaurant.Enums.ResultEnum;
import com.lzy.liujing.restaurant.dao.DeskDao;
import com.lzy.liujing.restaurant.entity.CustomPageInfo;
import com.lzy.liujing.restaurant.entity.Desk;
import com.lzy.liujing.restaurant.exception.CustomException;
import com.lzy.liujing.restaurant.service.DeskService;
import com.lzy.liujing.restaurant.utils.SplitIdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:LiuJing
 * Date:2018/10/22
 * Time:15:09
 *
 * 桌位管理业务逻辑
 */
@Service
@Transactional
public class DeskServiceImpl implements DeskService {
    @Autowired
    private DeskDao deskDao;
    /**
     * 分页查询桌位(可以加多条件)
     * @return
     */
    @Override
    public CustomPageInfo<Desk> findPage(CustomPageInfo<Desk> pageInfo) {
        Page page = PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        //此处调用查询后，返回值会返回到page中
        deskDao.findPage(pageInfo.getT());
        CustomPageInfo<Desk> resultInfo = new CustomPageInfo<>(page);
        return resultInfo;
    }
    /**
     * 根据id查找桌位
     * @param id
     * @return
     */
    @Override
    public Desk findById(Integer id) {
        return deskDao.findById(id);
    }


    /**
     *添加桌位
     * @param desk
     */
    @Override
    public void insert(Desk desk) {
        Desk findDesk = deskDao.findByDeskCode(desk);
        if(findDesk!=null){
            throw new CustomException(ResultEnum.DESK_CODE_IS_EXIST);
        }
        desk.setCreateTime(new Date());
        deskDao.insert(desk);
    }

    /**
     * 修改桌位信息
     * @param desk
     */
    @Override
    public void update(Desk desk) {
        Desk findDesk = deskDao.findByDeskCode(desk);
        if(findDesk!=null){
            throw new CustomException(ResultEnum.DESK_CODE_IS_EXIST);
        }
        desk.setModifyTime(new Date());
        deskDao.update(desk);
    }

    /**
     * 根据桌位id删除桌位
     * @param strIds
     */
    @Override
    public void deleteByIds(String strIds) {
        List<Integer> idList = SplitIdsUtil.splitStrIdsByInt(strIds);
       int effect =  deskDao.deleteByIds(idList);
       if(effect<=0){
           throw new CustomException(ResultEnum.DEL_DB_FAIL);
       }
    }

    /**
     * 根据桌号查询
     * @param desk
     * @return
     */
    @Override
    public Desk findByDeskCode(Desk desk) {
        return deskDao.findByDeskCode(desk);
    }

    /**
     * 桌位登录业务
     * @param desk
     */
    @Override
    public void login(Desk desk) {
       Desk findDesk =  deskDao.findByDeskCode(desk);
       if(findDesk==null){
           throw new CustomException(ResultEnum.DESK_CODE_NO_EXIST);
       }
       if(findDesk.getIdleStatus()==1||findDesk.getIdleStatus()==2){
           throw new CustomException(ResultEnum.DESK_CODE_NO_IDLE);
       }
       //将桌位设置成有人状态
       desk.setIdleStatus(1);
       deskDao.update(desk);
    }

    @Override
    public void logout(Desk desk){
        //将桌位状态设置为空闲0
        desk.setIdleStatus(0);
        deskDao.update(desk);
    }
}
