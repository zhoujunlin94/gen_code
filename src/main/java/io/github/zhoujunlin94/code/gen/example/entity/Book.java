package io.github.zhoujunlin94.code.gen.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scrm_book")
public class Book {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 投放xfeild
     */
    @Column(name = "xfield")
    private String xfield;

    /**
     * 图书名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 图书简介
     */
    @Column(name = "book_desc")
    private String bookDesc;

    /**
     * 图书价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 图书封面
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 购买链接
     */
    @Column(name = "buy_link")
    private String buyLink;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 老师名称
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 老师描述
     */
    @Column(name = "teacher_desc")
    private String teacherDesc;

    /**
     * 老师头像
     */
    @Column(name = "button_text")
    private String buttonText;

    /**
     * 老师头像
     */
    @Column(name = "teacher_avatar")
    private String teacherAvatar;

    /**
     * 老师二维码
     */
    @Column(name = "teacher_qrcode")
    private String teacherQrcode;

    /**
     * 老师弹窗的第一个二维码自定义文案
     */
    @Column(name = "first_custom_message")
    private String firstCustomMessage;

    /**
     * 老师弹窗的第二个二维码自定义文案
     */
    @Column(name = "second_custom_message")
    private String secondCustomMessage;

    /**
     * 上架状态
     */
    @Column(name = "book_status")
    private Integer bookStatus;

    /**
     * 图书二维码
     */
    @Column(name = "book_qrcode")
    private String bookQrcode;

    /**
     * 逻辑删除标志位
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 科目ID
     */
    @Column(name = "subject_id")
    private Integer subjectId;

    /**
     * 图书标签文案（深）
     */
    @Column(name = "dark_tag_text")
    private String darkTagText;

    /**
     * 图书标签文案（浅）
     */
    @Column(name = "light_tag_text")
    private String lightTagText;

    /**
     * 图书推荐内容配置开关 0否1是
     */
    @Column(name = "recommend_state")
    private Integer recommendState;

    /**
     * 图书推荐封面图片 uri
     */
    @Column(name = "recommend_photo_url")
    private String recommendPhotoUrl;

    /**
     * 图书推荐按钮文案
     */
    @Column(name = "recommend_button_text")
    private String recommendButtonText;

    /**
     * 图书推荐文案
     */
    @Column(name = "recommend_text")
    private String recommendText;

    /**
     * 悬浮按钮配置开关 0否1是
     */
    @Column(name = "hover_button_state")
    private Integer hoverButtonState;

    /**
     * 悬浮按钮图片 uri
     */
    @Column(name = "hover_button_photo_url")
    private String hoverButtonPhotoUrl;

    /**
     * 悬浮按钮跳转链接
     */
    @Column(name = "hover_button_to_link")
    private String hoverButtonToLink;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

    /**
     * 开启模考：0-未开启，1-已开启
     */
    @Column(name = "open_simulation_exam")
    private Integer openSimulationExam;

}