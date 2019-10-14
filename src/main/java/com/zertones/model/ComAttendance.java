package com.zertones.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zertones.model.common.LectureTheoryTime;
import com.zertones.model.master.ComStaffSubject;
import com.zertones.system.utility.JsonDateDeserializer;
import com.zertones.system.utility.JsonDateSerializer;

@Entity
@Table(name = "COM_ATTENDANCE_SHEET")
public class ComAttendance extends BaseModel implements Serializable
{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer				id;

	@Column(name = "DEP_ID")
	private String				Dep_Id;

	@Column(name = "ATTEN_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date				class_date;

	@ManyToOne
	@JoinColumn(name = "STAFF_SUB_ID", nullable = false)
	private ComStaffSubject		comStaffSubject;

	@Column(name = "DIVISION")
	private String				division;

	@Column(name = "SEM")
	private Integer				sem;

	@Column(name = "YEAR")
	private Integer				year;

	@Column(name = "ROLE_NO_1")
	public Integer				roll_1;

	@Column(name = "ROLE_NO_2")
	public Integer				roll_2;

	@Column(name = "ROLE_NO_3")
	public Integer				roll_3;

	@Column(name = "ROLE_NO_4")
	public Integer				roll_4;

	@Column(name = "ROLE_NO_5")
	public Integer				roll_5;

	@Column(name = "ROLE_NO_6")
	public Integer				roll_6;

	@Column(name = "ROLE_NO_7")
	public Integer				roll_7;

	@Column(name = "ROLE_NO_8")
	public Integer				roll_8;

	@Column(name = "ROLE_NO_9")
	public Integer				roll_9;

	@Column(name = "ROLE_NO_10")
	public Integer				roll_10;

	@Column(name = "ROLE_NO_11")
	public Integer				roll_11;

	@Column(name = "ROLE_NO_12")
	public Integer				roll_12;

	@Column(name = "ROLE_NO_13")
	public Integer				roll_13;

	@Column(name = "ROLE_NO_14")
	public Integer				roll_14;

	@Column(name = "ROLE_NO_15")
	public Integer				roll_15;

	@Column(name = "ROLE_NO_16")
	public Integer				roll_16;

	@Column(name = "ROLE_NO_17")
	public Integer				roll_17;

	@Column(name = "ROLE_NO_18")
	public Integer				roll_18;

	@Column(name = "ROLE_NO_19")
	public Integer				roll_19;

	@Column(name = "ROLE_NO_20")
	public Integer				roll_20;

	@Column(name = "ROLE_NO_21")
	public Integer				roll_21;

	@Column(name = "ROLE_NO_22")
	public Integer				roll_22;

	@Column(name = "ROLE_NO_23")
	public Integer				roll_23;

	@Column(name = "ROLE_NO_24")
	public Integer				roll_24;

	@Column(name = "ROLE_NO_25")
	public Integer				roll_25;

	@Column(name = "ROLE_NO_26")
	public Integer				roll_26;

	@Column(name = "ROLE_NO_27")
	public Integer				roll_27;

	@Column(name = "ROLE_NO_28")
	public Integer				roll_28;

	@Column(name = "ROLE_NO_29")
	public Integer				roll_29;

	@Column(name = "ROLE_NO_30")
	public Integer				roll_30;

	@Column(name = "ROLE_NO_31")
	public Integer				roll_31;

	@Column(name = "ROLE_NO_32")
	public Integer				roll_32;

	@Column(name = "ROLE_NO_33")
	public Integer				roll_33;

	@Column(name = "ROLE_NO_34")
	public Integer				roll_34;

	@Column(name = "ROLE_NO_35")
	public Integer				roll_35;

	@Column(name = "ROLE_NO_36")
	public Integer				roll_36;

	@Column(name = "ROLE_NO_37")
	public Integer				roll_37;

	@Column(name = "ROLE_NO_38")
	public Integer				roll_38;

	@Column(name = "ROLE_NO_39")
	public Integer				roll_39;

	@Column(name = "ROLE_NO_40")
	public Integer				roll_40;

	@Column(name = "ROLE_NO_41")
	public Integer				roll_41;

	@Column(name = "ROLE_NO_42")
	public Integer				roll_42;

	@Column(name = "ROLE_NO_43")
	public Integer				roll_43;

	@Column(name = "ROLE_NO_44")
	public Integer				roll_44;

	@Column(name = "ROLE_NO_45")
	public Integer				roll_45;

	@Column(name = "ROLE_NO_46")
	public Integer				roll_46;

	@Column(name = "ROLE_NO_47")
	public Integer				roll_47;

	@Column(name = "ROLE_NO_48")
	public Integer				roll_48;

	@Column(name = "ROLE_NO_49")
	public Integer				roll_49;

	@Column(name = "ROLE_NO_50")
	public Integer				roll_50;

	@Column(name = "ROLE_NO_51")
	public Integer				roll_51;

	@Column(name = "ROLE_NO_52")
	public Integer				roll_52;

	@Column(name = "ROLE_NO_53")
	public Integer				roll_53;

	@Column(name = "ROLE_NO_54")
	public Integer				roll_54;

	@Column(name = "ROLE_NO_55")
	public Integer				roll_55;

	@Column(name = "ROLE_NO_56")
	public Integer				roll_56;

	@Column(name = "ROLE_NO_57")
	public Integer				roll_57;

	@Column(name = "ROLE_NO_58")
	public Integer				roll_58;

	@Column(name = "ROLE_NO_59")
	public Integer				roll_59;

	@Column(name = "ROLE_NO_60")
	public Integer				roll_60;

	@Column(name = "ROLE_NO_61")
	public Integer				roll_61;

	@Column(name = "ROLE_NO_62")
	public Integer				roll_62;

	@Column(name = "ROLE_NO_63")
	public Integer				roll_63;

	@Column(name = "ROLE_NO_64")
	public Integer				roll_64;

	@Column(name = "ROLE_NO_65")
	public Integer				roll_65;

	@Column(name = "ROLE_NO_66")
	public Integer				roll_66;

	@Column(name = "ROLE_NO_67")
	public Integer				roll_67;

	@Column(name = "ROLE_NO_68")
	public Integer				roll_68;

	@Column(name = "ROLE_NO_69")
	public Integer				roll_69;

	@Column(name = "ROLE_NO_70")
	public Integer				roll_70;

	@Column(name = "ROLE_NO_71")
	public Integer				roll_71;

	@Column(name = "ROLE_NO_72")
	public Integer				roll_72;

	@Column(name = "ROLE_NO_73")
	public Integer				roll_73;

	@Column(name = "ROLE_NO_74")
	public Integer				roll_74;

	@Column(name = "ROLE_NO_75")
	public Integer				roll_75;

	@Column(name = "ROLE_NO_76")
	public Integer				roll_76;

	@Column(name = "ROLE_NO_77")
	public Integer				roll_77;

	@Column(name = "ROLE_NO_78")
	public Integer				roll_78;

	@Column(name = "ROLE_NO_79")
	public Integer				roll_79;

	@Column(name = "ROLE_NO_80")
	public Integer				roll_80;

	@Column(name = "ROLE_NO_81")
	public Integer				roll_81;

	@Column(name = "ROLE_NO_82")
	public Integer				roll_82;

	@Column(name = "ROLE_NO_83")
	public Integer				roll_83;

	@Column(name = "ROLE_NO_84")
	public Integer				roll_84;

	@Column(name = "ROLE_NO_85")
	public Integer				roll_85;

	@Column(name = "ROLE_NO_86")
	public Integer				roll_86;

	@Column(name = "ROLE_NO_87")
	public Integer				roll_87;

	@Column(name = "ROLE_NO_88")
	public Integer				roll_88;

	@Column(name = "ROLE_NO_89")
	public Integer				roll_89;

	@Column(name = "ROLE_NO_90")
	public Integer				roll_90;

	@Column(name = "ROLE_NO_91")
	public Integer				roll_91;

	@Column(name = "ROLE_NO_92")
	public Integer				roll_92;

	@Column(name = "ROLE_NO_93")
	public Integer				roll_93;

	@Column(name = "ROLE_NO_94")
	public Integer				roll_94;

	@Column(name = "ROLE_NO_95")
	public Integer				roll_95;

	@Column(name = "ROLE_NO_96")
	public Integer				roll_96;

	@Column(name = "ROLE_NO_97")
	public Integer				roll_97;

	@Column(name = "ROLE_NO_98")
	public Integer				roll_98;

	@Column(name = "ROLE_NO_99")
	public Integer				roll_99;

	@Column(name = "ROLE_NO_100")
	public Integer				roll_100;

	@Column(name = "ROLE_NO_101")
	public Integer				roll_101;

	@Column(name = "ROLE_NO_102")
	public Integer				roll_102;

	@Column(name = "ROLE_NO_103")
	public Integer				roll_103;

	@Column(name = "ROLE_NO_104")
	public Integer				roll_104;

	@Column(name = "ROLE_NO_105")
	public Integer				roll_105;

	@Column(name = "ROLE_NO_106")
	public Integer				roll_106;

	@Column(name = "ROLE_NO_107")
	public Integer				roll_107;

	@Column(name = "ROLE_NO_108")
	public Integer				roll_108;

	@Column(name = "ROLE_NO_109")
	public Integer				roll_109;

	@Column(name = "ROLE_NO_110")
	public Integer				roll_110;

	@Column(name = "ROLE_NO_111")
	public Integer				roll_111;

	@Column(name = "ROLE_NO_112")
	public Integer				roll_112;

	@Column(name = "ROLE_NO_113")
	public Integer				roll_113;

	@Column(name = "ROLE_NO_115")
	public Integer				roll_115;

	@Column(name = "ROLE_NO_116")
	public Integer				roll_116;

	@Column(name = "ROLE_NO_117")
	public Integer				roll_117;

	@Column(name = "ROLE_NO_118")
	public Integer				roll_118;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THE_NO")
	private LectureTheoryTime	lectureTheoryTime;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getDep_Id()
	{
		return Dep_Id;
	}

	public void setDep_Id(String dep_Id)
	{
		Dep_Id = dep_Id;
	}

	public Date getClass_date()
	{
		return class_date;
	}

	public void setClass_date(Date class_date)
	{
		this.class_date = class_date;
	}

	public ComStaffSubject getComStaffSubject()
	{
		return comStaffSubject;
	}

	public void setComStaffSubject(ComStaffSubject comStaffSubject)
	{
		this.comStaffSubject = comStaffSubject;
	}

	public String getDivision()
	{
		return division;
	}

	public void setDivision(String division)
	{
		this.division = division;
	}

	public Integer getSem()
	{
		return sem;
	}

	public void setSem(Integer sem)
	{
		this.sem = sem;
	}

	public Integer getYear()
	{
		return year;
	}

	public void setYear(Integer year)
	{
		this.year = year;
	}

	public Integer getRoll_1()
	{
		return roll_1;
	}

	public void setRoll_1(Integer roll_1)
	{
		this.roll_1 = roll_1;
	}

	public Integer getRoll_2()
	{
		return roll_2;
	}

	public void setRoll_2(Integer roll_2)
	{
		this.roll_2 = roll_2;
	}

	public Integer getRoll_3()
	{
		return roll_3;
	}

	public void setRoll_3(Integer roll_3)
	{
		this.roll_3 = roll_3;
	}

	public Integer getRoll_4()
	{
		return roll_4;
	}

	public void setRoll_4(Integer roll_4)
	{
		this.roll_4 = roll_4;
	}

	public Integer getRoll_5()
	{
		return roll_5;
	}

	public void setRoll_5(Integer roll_5)
	{
		this.roll_5 = roll_5;
	}

	public Integer getRoll_6()
	{
		return roll_6;
	}

	public void setRoll_6(Integer roll_6)
	{
		this.roll_6 = roll_6;
	}

	public Integer getRoll_7()
	{
		return roll_7;
	}

	public void setRoll_7(Integer roll_7)
	{
		this.roll_7 = roll_7;
	}

	public Integer getRoll_8()
	{
		return roll_8;
	}

	public void setRoll_8(Integer roll_8)
	{
		this.roll_8 = roll_8;
	}

	public Integer getRoll_9()
	{
		return roll_9;
	}

	public void setRoll_9(Integer roll_9)
	{
		this.roll_9 = roll_9;
	}

	public Integer getRoll_10()
	{
		return roll_10;
	}

	public void setRoll_10(Integer roll_10)
	{
		this.roll_10 = roll_10;
	}

	public Integer getRoll_11()
	{
		return roll_11;
	}

	public void setRoll_11(Integer roll_11)
	{
		this.roll_11 = roll_11;
	}

	public Integer getRoll_12()
	{
		return roll_12;
	}

	public void setRoll_12(Integer roll_12)
	{
		this.roll_12 = roll_12;
	}

	public Integer getRoll_13()
	{
		return roll_13;
	}

	public void setRoll_13(Integer roll_13)
	{
		this.roll_13 = roll_13;
	}

	public Integer getRoll_14()
	{
		return roll_14;
	}

	public void setRoll_14(Integer roll_14)
	{
		this.roll_14 = roll_14;
	}

	public Integer getRoll_15()
	{
		return roll_15;
	}

	public void setRoll_15(Integer roll_15)
	{
		this.roll_15 = roll_15;
	}

	public Integer getRoll_16()
	{
		return roll_16;
	}

	public void setRoll_16(Integer roll_16)
	{
		this.roll_16 = roll_16;
	}

	public Integer getRoll_17()
	{
		return roll_17;
	}

	public void setRoll_17(Integer roll_17)
	{
		this.roll_17 = roll_17;
	}

	public Integer getRoll_18()
	{
		return roll_18;
	}

	public void setRoll_18(Integer roll_18)
	{
		this.roll_18 = roll_18;
	}

	public Integer getRoll_19()
	{
		return roll_19;
	}

	public void setRoll_19(Integer roll_19)
	{
		this.roll_19 = roll_19;
	}

	public Integer getRoll_20()
	{
		return roll_20;
	}

	public void setRoll_20(Integer roll_20)
	{
		this.roll_20 = roll_20;
	}

	public Integer getRoll_21()
	{
		return roll_21;
	}

	public void setRoll_21(Integer roll_21)
	{
		this.roll_21 = roll_21;
	}

	public Integer getRoll_22()
	{
		return roll_22;
	}

	public void setRoll_22(Integer roll_22)
	{
		this.roll_22 = roll_22;
	}

	public Integer getRoll_23()
	{
		return roll_23;
	}

	public void setRoll_23(Integer roll_23)
	{
		this.roll_23 = roll_23;
	}

	public Integer getRoll_24()
	{
		return roll_24;
	}

	public void setRoll_24(Integer roll_24)
	{
		this.roll_24 = roll_24;
	}

	public Integer getRoll_25()
	{
		return roll_25;
	}

	public void setRoll_25(Integer roll_25)
	{
		this.roll_25 = roll_25;
	}

	public Integer getRoll_26()
	{
		return roll_26;
	}

	public void setRoll_26(Integer roll_26)
	{
		this.roll_26 = roll_26;
	}

	public Integer getRoll_27()
	{
		return roll_27;
	}

	public void setRoll_27(Integer roll_27)
	{
		this.roll_27 = roll_27;
	}

	public Integer getRoll_28()
	{
		return roll_28;
	}

	public void setRoll_28(Integer roll_28)
	{
		this.roll_28 = roll_28;
	}

	public Integer getRoll_29()
	{
		return roll_29;
	}

	public void setRoll_29(Integer roll_29)
	{
		this.roll_29 = roll_29;
	}

	public Integer getRoll_30()
	{
		return roll_30;
	}

	public void setRoll_30(Integer roll_30)
	{
		this.roll_30 = roll_30;
	}

	public Integer getRoll_31()
	{
		return roll_31;
	}

	public void setRoll_31(Integer roll_31)
	{
		this.roll_31 = roll_31;
	}

	public Integer getRoll_32()
	{
		return roll_32;
	}

	public void setRoll_32(Integer roll_32)
	{
		this.roll_32 = roll_32;
	}

	public Integer getRoll_33()
	{
		return roll_33;
	}

	public void setRoll_33(Integer roll_33)
	{
		this.roll_33 = roll_33;
	}

	public Integer getRoll_34()
	{
		return roll_34;
	}

	public void setRoll_34(Integer roll_34)
	{
		this.roll_34 = roll_34;
	}

	public Integer getRoll_35()
	{
		return roll_35;
	}

	public void setRoll_35(Integer roll_35)
	{
		this.roll_35 = roll_35;
	}

	public Integer getRoll_36()
	{
		return roll_36;
	}

	public void setRoll_36(Integer roll_36)
	{
		this.roll_36 = roll_36;
	}

	public Integer getRoll_37()
	{
		return roll_37;
	}

	public void setRoll_37(Integer roll_37)
	{
		this.roll_37 = roll_37;
	}

	public Integer getRoll_38()
	{
		return roll_38;
	}

	public void setRoll_38(Integer roll_38)
	{
		this.roll_38 = roll_38;
	}

	public Integer getRoll_39()
	{
		return roll_39;
	}

	public void setRoll_39(Integer roll_39)
	{
		this.roll_39 = roll_39;
	}

	public Integer getRoll_40()
	{
		return roll_40;
	}

	public void setRoll_40(Integer roll_40)
	{
		this.roll_40 = roll_40;
	}

	public Integer getRoll_41()
	{
		return roll_41;
	}

	public void setRoll_41(Integer roll_41)
	{
		this.roll_41 = roll_41;
	}

	public Integer getRoll_42()
	{
		return roll_42;
	}

	public void setRoll_42(Integer roll_42)
	{
		this.roll_42 = roll_42;
	}

	public Integer getRoll_43()
	{
		return roll_43;
	}

	public void setRoll_43(Integer roll_43)
	{
		this.roll_43 = roll_43;
	}

	public Integer getRoll_44()
	{
		return roll_44;
	}

	public void setRoll_44(Integer roll_44)
	{
		this.roll_44 = roll_44;
	}

	public Integer getRoll_45()
	{
		return roll_45;
	}

	public void setRoll_45(Integer roll_45)
	{
		this.roll_45 = roll_45;
	}

	public Integer getRoll_46()
	{
		return roll_46;
	}

	public void setRoll_46(Integer roll_46)
	{
		this.roll_46 = roll_46;
	}

	public Integer getRoll_47()
	{
		return roll_47;
	}

	public void setRoll_47(Integer roll_47)
	{
		this.roll_47 = roll_47;
	}

	public Integer getRoll_48()
	{
		return roll_48;
	}

	public void setRoll_48(Integer roll_48)
	{
		this.roll_48 = roll_48;
	}

	public Integer getRoll_49()
	{
		return roll_49;
	}

	public void setRoll_49(Integer roll_49)
	{
		this.roll_49 = roll_49;
	}

	public Integer getRoll_50()
	{
		return roll_50;
	}

	public void setRoll_50(Integer roll_50)
	{
		this.roll_50 = roll_50;
	}

	public Integer getRoll_51()
	{
		return roll_51;
	}

	public void setRoll_51(Integer roll_51)
	{
		this.roll_51 = roll_51;
	}

	public Integer getRoll_52()
	{
		return roll_52;
	}

	public void setRoll_52(Integer roll_52)
	{
		this.roll_52 = roll_52;
	}

	public Integer getRoll_53()
	{
		return roll_53;
	}

	public void setRoll_53(Integer roll_53)
	{
		this.roll_53 = roll_53;
	}

	public Integer getRoll_54()
	{
		return roll_54;
	}

	public void setRoll_54(Integer roll_54)
	{
		this.roll_54 = roll_54;
	}

	public Integer getRoll_55()
	{
		return roll_55;
	}

	public void setRoll_55(Integer roll_55)
	{
		this.roll_55 = roll_55;
	}

	public Integer getRoll_56()
	{
		return roll_56;
	}

	public void setRoll_56(Integer roll_56)
	{
		this.roll_56 = roll_56;
	}

	public Integer getRoll_57()
	{
		return roll_57;
	}

	public void setRoll_57(Integer roll_57)
	{
		this.roll_57 = roll_57;
	}

	public Integer getRoll_58()
	{
		return roll_58;
	}

	public void setRoll_58(Integer roll_58)
	{
		this.roll_58 = roll_58;
	}

	public Integer getRoll_59()
	{
		return roll_59;
	}

	public void setRoll_59(Integer roll_59)
	{
		this.roll_59 = roll_59;
	}

	public Integer getRoll_60()
	{
		return roll_60;
	}

	public void setRoll_60(Integer roll_60)
	{
		this.roll_60 = roll_60;
	}

	public Integer getRoll_61()
	{
		return roll_61;
	}

	public void setRoll_61(Integer roll_61)
	{
		this.roll_61 = roll_61;
	}

	public Integer getRoll_62()
	{
		return roll_62;
	}

	public void setRoll_62(Integer roll_62)
	{
		this.roll_62 = roll_62;
	}

	public Integer getRoll_63()
	{
		return roll_63;
	}

	public void setRoll_63(Integer roll_63)
	{
		this.roll_63 = roll_63;
	}

	public Integer getRoll_64()
	{
		return roll_64;
	}

	public void setRoll_64(Integer roll_64)
	{
		this.roll_64 = roll_64;
	}

	public Integer getRoll_65()
	{
		return roll_65;
	}

	public void setRoll_65(Integer roll_65)
	{
		this.roll_65 = roll_65;
	}

	public Integer getRoll_66()
	{
		return roll_66;
	}

	public void setRoll_66(Integer roll_66)
	{
		this.roll_66 = roll_66;
	}

	public Integer getRoll_67()
	{
		return roll_67;
	}

	public void setRoll_67(Integer roll_67)
	{
		this.roll_67 = roll_67;
	}

	public Integer getRoll_68()
	{
		return roll_68;
	}

	public void setRoll_68(Integer roll_68)
	{
		this.roll_68 = roll_68;
	}

	public Integer getRoll_69()
	{
		return roll_69;
	}

	public void setRoll_69(Integer roll_69)
	{
		this.roll_69 = roll_69;
	}

	public Integer getRoll_70()
	{
		return roll_70;
	}

	public void setRoll_70(Integer roll_70)
	{
		this.roll_70 = roll_70;
	}

	public Integer getRoll_71()
	{
		return roll_71;
	}

	public void setRoll_71(Integer roll_71)
	{
		this.roll_71 = roll_71;
	}

	public Integer getRoll_72()
	{
		return roll_72;
	}

	public void setRoll_72(Integer roll_72)
	{
		this.roll_72 = roll_72;
	}

	public Integer getRoll_73()
	{
		return roll_73;
	}

	public void setRoll_73(Integer roll_73)
	{
		this.roll_73 = roll_73;
	}

	public Integer getRoll_74()
	{
		return roll_74;
	}

	public void setRoll_74(Integer roll_74)
	{
		this.roll_74 = roll_74;
	}

	public Integer getRoll_75()
	{
		return roll_75;
	}

	public void setRoll_75(Integer roll_75)
	{
		this.roll_75 = roll_75;
	}

	public Integer getRoll_76()
	{
		return roll_76;
	}

	public void setRoll_76(Integer roll_76)
	{
		this.roll_76 = roll_76;
	}

	public Integer getRoll_77()
	{
		return roll_77;
	}

	public void setRoll_77(Integer roll_77)
	{
		this.roll_77 = roll_77;
	}

	public Integer getRoll_78()
	{
		return roll_78;
	}

	public void setRoll_78(Integer roll_78)
	{
		this.roll_78 = roll_78;
	}

	public Integer getRoll_79()
	{
		return roll_79;
	}

	public void setRoll_79(Integer roll_79)
	{
		this.roll_79 = roll_79;
	}

	public Integer getRoll_80()
	{
		return roll_80;
	}

	public void setRoll_80(Integer roll_80)
	{
		this.roll_80 = roll_80;
	}

	public Integer getRoll_81()
	{
		return roll_81;
	}

	public void setRoll_81(Integer roll_81)
	{
		this.roll_81 = roll_81;
	}

	public Integer getRoll_82()
	{
		return roll_82;
	}

	public void setRoll_82(Integer roll_82)
	{
		this.roll_82 = roll_82;
	}

	public Integer getRoll_83()
	{
		return roll_83;
	}

	public void setRoll_83(Integer roll_83)
	{
		this.roll_83 = roll_83;
	}

	public Integer getRoll_84()
	{
		return roll_84;
	}

	public void setRoll_84(Integer roll_84)
	{
		this.roll_84 = roll_84;
	}

	public Integer getRoll_85()
	{
		return roll_85;
	}

	public void setRoll_85(Integer roll_85)
	{
		this.roll_85 = roll_85;
	}

	public Integer getRoll_86()
	{
		return roll_86;
	}

	public void setRoll_86(Integer roll_86)
	{
		this.roll_86 = roll_86;
	}

	public Integer getRoll_87()
	{
		return roll_87;
	}

	public void setRoll_87(Integer roll_87)
	{
		this.roll_87 = roll_87;
	}

	public Integer getRoll_88()
	{
		return roll_88;
	}

	public void setRoll_88(Integer roll_88)
	{
		this.roll_88 = roll_88;
	}

	public Integer getRoll_89()
	{
		return roll_89;
	}

	public void setRoll_89(Integer roll_89)
	{
		this.roll_89 = roll_89;
	}

	public Integer getRoll_90()
	{
		return roll_90;
	}

	public void setRoll_90(Integer roll_90)
	{
		this.roll_90 = roll_90;
	}

	public Integer getRoll_91()
	{
		return roll_91;
	}

	public void setRoll_91(Integer roll_91)
	{
		this.roll_91 = roll_91;
	}

	public Integer getRoll_92()
	{
		return roll_92;
	}

	public void setRoll_92(Integer roll_92)
	{
		this.roll_92 = roll_92;
	}

	public Integer getRoll_93()
	{
		return roll_93;
	}

	public void setRoll_93(Integer roll_93)
	{
		this.roll_93 = roll_93;
	}

	public Integer getRoll_94()
	{
		return roll_94;
	}

	public void setRoll_94(Integer roll_94)
	{
		this.roll_94 = roll_94;
	}

	public Integer getRoll_95()
	{
		return roll_95;
	}

	public void setRoll_95(Integer roll_95)
	{
		this.roll_95 = roll_95;
	}

	public Integer getRoll_96()
	{
		return roll_96;
	}

	public void setRoll_96(Integer roll_96)
	{
		this.roll_96 = roll_96;
	}

	public Integer getRoll_97()
	{
		return roll_97;
	}

	public void setRoll_97(Integer roll_97)
	{
		this.roll_97 = roll_97;
	}

	public Integer getRoll_98()
	{
		return roll_98;
	}

	public void setRoll_98(Integer roll_98)
	{
		this.roll_98 = roll_98;
	}

	public Integer getRoll_99()
	{
		return roll_99;
	}

	public void setRoll_99(Integer roll_99)
	{
		this.roll_99 = roll_99;
	}

	public Integer getRoll_100()
	{
		return roll_100;
	}

	public void setRoll_100(Integer roll_100)
	{
		this.roll_100 = roll_100;
	}

	public Integer getRoll_101()
	{
		return roll_101;
	}

	public void setRoll_101(Integer roll_101)
	{
		this.roll_101 = roll_101;
	}

	public Integer getRoll_102()
	{
		return roll_102;
	}

	public void setRoll_102(Integer roll_102)
	{
		this.roll_102 = roll_102;
	}

	public Integer getRoll_103()
	{
		return roll_103;
	}

	public void setRoll_103(Integer roll_103)
	{
		this.roll_103 = roll_103;
	}

	public Integer getRoll_104()
	{
		return roll_104;
	}

	public void setRoll_104(Integer roll_104)
	{
		this.roll_104 = roll_104;
	}

	public Integer getRoll_105()
	{
		return roll_105;
	}

	public void setRoll_105(Integer roll_105)
	{
		this.roll_105 = roll_105;
	}

	public Integer getRoll_106()
	{
		return roll_106;
	}

	public void setRoll_106(Integer roll_106)
	{
		this.roll_106 = roll_106;
	}

	public Integer getRoll_107()
	{
		return roll_107;
	}

	public void setRoll_107(Integer roll_107)
	{
		this.roll_107 = roll_107;
	}

	public Integer getRoll_108()
	{
		return roll_108;
	}

	public void setRoll_108(Integer roll_108)
	{
		this.roll_108 = roll_108;
	}

	public Integer getRoll_109()
	{
		return roll_109;
	}

	public void setRoll_109(Integer roll_109)
	{
		this.roll_109 = roll_109;
	}

	public Integer getRoll_110()
	{
		return roll_110;
	}

	public void setRoll_110(Integer roll_110)
	{
		this.roll_110 = roll_110;
	}

	public Integer getRoll_111()
	{
		return roll_111;
	}

	public void setRoll_111(Integer roll_111)
	{
		this.roll_111 = roll_111;
	}

	public Integer getRoll_112()
	{
		return roll_112;
	}

	public void setRoll_112(Integer roll_112)
	{
		this.roll_112 = roll_112;
	}

	public Integer getRoll_113()
	{
		return roll_113;
	}

	public void setRoll_113(Integer roll_113)
	{
		this.roll_113 = roll_113;
	}

	public Integer getRoll_115()
	{
		return roll_115;
	}

	public void setRoll_115(Integer roll_115)
	{
		this.roll_115 = roll_115;
	}

	public Integer getRoll_116()
	{
		return roll_116;
	}

	public void setRoll_116(Integer roll_116)
	{
		this.roll_116 = roll_116;
	}

	public Integer getRoll_117()
	{
		return roll_117;
	}

	public void setRoll_117(Integer roll_117)
	{
		this.roll_117 = roll_117;
	}

	public Integer getRoll_118()
	{
		return roll_118;
	}

	public void setRoll_118(Integer roll_118)
	{
		this.roll_118 = roll_118;
	}

	public LectureTheoryTime getLectureTheoryTime()
	{
		return lectureTheoryTime;
	}

	public void setLectureTheoryTime(LectureTheoryTime lectureTheoryTime)
	{
		this.lectureTheoryTime = lectureTheoryTime;
	}

}
