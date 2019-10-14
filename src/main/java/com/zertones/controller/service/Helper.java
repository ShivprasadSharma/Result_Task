package com.zertones.controller.service;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zertones.model.BaseModel;
import com.zertones.model.ResultMarksheet;
import com.zertones.model.ResultStudent;
import com.zertones.model.DataTransferObjectModel.DepartmentDTO;
import com.zertones.service.common.CommonService;

@Component
public class Helper extends BaseModel implements Serializable
{
	@Autowired
	private CommonService	commonService;

	String					sub			= null;
	String					seatNo		= "--";
	String					prn			= "";
	Integer					exam_year	= 0;
	String					acYr		= "";
	String					collegename	= "";
	int						no			= 0;
	int						l			= 0;

	/* first file coding start */

	ResultMarksheet			sheet		= null;
	ResultStudent			student		= null;

	String					semister	= "";
	String					total_earn	= "";
	String					sgpa		= "";
	String					lastWord	= "";
	Integer					pattern		= 0;
	String					branchname	= "";
	String					seatno		= "";
	ResultStudent			student1	= new ResultStudent();
	Integer					bid			= 1;

	public void newsave(String element, int x) /* first result file */
	{

		sheet = new ResultMarksheet();
		student = new ResultStudent();

		System.out.println("Count:" + x);
		String[] str = null;
		if (x == 0)
		{
			String[] yr = element.substring(element.indexOf(",")).trim().split("\\,");
			acYr = yr[1].substring(0, yr[1].indexOf("(")).trim();
			pattern = Integer.parseInt(yr[1].substring(yr[1].indexOf("(") + 1, yr[1].indexOf("(") + 6).trim());
			System.out.println("patternnew" + pattern);
			exam_year = Integer.parseInt(yr[2].substring(yr[2].indexOf(" ")).trim());

		}
		if (x == 2)
		{
			collegename = element.substring(element.indexOf(":") + 1, element.length());
		}
		if (x == 3)
		{

			Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(element);
			System.out.println("matcherclass" + m);
			int i = 1;
			String branch = "";
			System.out.println("b id.................." + m);

			if (m == null)
			{
				bid = 1;
			}

			while (m.find())
			{
				List<DepartmentDTO> dept = commonService.getDepartmentList();
				System.out.println("departmentlisttttttttt" + dept);
				if (i == 2)
				{
					branch = m.group(1);
				}
				i++;

				if (branch.equalsIgnoreCase("null"))
				{
					bid = 1;
					System.out.println("branchid" + bid);

				} else
				{
					for (DepartmentDTO deptlist : dept)
					{
						if ((deptlist.getDep_name().equalsIgnoreCase(branch)))
						{
							System.out.println("departmentnameeeeeeeeee" + deptlist.getDep_name());
							bid = deptlist.getDep_id();
						}
					}
				}
			}

		}

		String nameLine[] = element.split(" ");

		if (nameLine.length > 2)
		{
			if (x > 6)
			{

				if (nameLine[0].length() == 10) // null pointer exception
				{
					System.out.println("lenght 10");
					System.out.println("namelinnnnnnnnn" + nameLine[0]);
					String firstnameLineing = nameLine[0].substring(1, nameLine[0].length());
					if (nameLine[0].substring(0, 1).matches("[A-Z/a-z]") && firstnameLineing.matches("[0-9]+"))
					{
						System.out.println("printinsi:::" + nameLine[0]);
						if (nameLine.length == 7 || nameLine.length > 7)
						{
							System.out.println("7 lenght");
							System.out.println("sr name:" + nameLine[1]);
							System.out.println("first name aname:" + nameLine[2]);
							System.out.println("father name:" + nameLine[3]);
						} else if (nameLine.length == 6)
						{
							System.out.println("6 lenght");
							System.out.println("sr name:" + nameLine[1]);
							System.out.println("first aname:" + nameLine[2]);
							System.out.println("father name:");
						} else if (nameLine.length == 5)
						{
							System.out.println(" 5lenght");
							System.out.println("sr name:");
							System.out.println("first aname:" + nameLine[1]);
							System.out.println("father name:");
						}
						seatno = nameLine[0];
						lastWord = nameLine[nameLine.length - 2];

						System.out.println("prn number" + lastWord);

					}

					student.setFirstName(nameLine[2]);
					student.setMiddleName(nameLine[3]);
					student.setLastName(nameLine[1]);
					student.setSeatno(nameLine[0]);
					student.setPrnumber(lastWord);
					student.setAcademiyr(acYr);
					student.setYear(exam_year);
					student.setBranch(bid);
					student.setStudcollegename(collegename);
					student1 = commonService.saveStudentResult(student);

					// student1 = commonService.saveStudentResult(student);

					System.out.println("object............. return... " + student1);
				}
			}
		}
		if (element.contains("SEM.:"))
		{
			semister = element.substring(element.indexOf(":") + 1, element.length());
			System.out.println("rohanbhau" + semister);

		}

		System.out.println("emenent length::::" + element.length());
		if ((element.contains("/")) && (element.length() == 70 || element.length() == 69 || element.length() == 71
				|| element.length() == 72))
		{
			sheet = new ResultMarksheet();
			sheet.setAcademiyr(acYr);
			sheet.setPattern(pattern);
			sheet.setYear(exam_year);
			sheet.setCollegename(collegename);
			// sheet.setseatno(seatNo);
			// sheet.setSeat_no(seatno);
			System.out.println("nameeeeeeeeee" + seatno);
			System.out.println("sheeeetprnnn" + lastWord);
			sheet.setSemister(Integer.parseInt(semister));
			sheet.setBranch(String.valueOf(bid));
			String[] str1 = element.split("\\s");

			int length = element.length();

			System.out.println("length1" + length);

			if (element.contains("*"))
			{
				str1 = (String[]) ArrayUtils.remove(str1, 2);
			}
			System.out.println("length2" + length);

			System.out.println("subjectCode" + str1[1]);
			String subjectCode = str1[1];
			sheet.setIn_obtained(checkString(str1[2]));
			System.out.println("internal" + checkString(str1[2]));

			sheet.setTh_obtained(checkString(str1[3]));
			System.out.println("thery" + checkString(str1[3]));

			sheet.setTo_obtained(checkString(str1[4]));
			System.out.println("total" + checkString(str1[4]));

			sheet.setTw_obtained(checkString(str1[5]));
			System.out.println("rohan" + checkString(str1[5]));

			sheet.setPr_obtained(checkString(str1[6]));
			System.out.println("practical" + checkString(str1[6]));

			sheet.setOl_obtained(checkString(str1[7]));
			System.out.println("overal" + checkString(str1[7]));

			sheet.setTotal_percenatge(str1[8]);
			System.out.println("totalper" + str1[8]);

			sheet.setCrd(str1[9]);
			System.out.println("crd" + str1[9]);

			sheet.setGrade(str1[10]);
			System.out.println("grd" + str1[10]);

			sheet.setGp(str1[11]);
			System.out.println("gp" + str1[11]);

			sheet.setCp(str1[12]);
			System.out.println("cp" + str1[12]);
			System.out.println("printstiudentshetttt" + student1);

			sheet.setSeat_no(student1.getSeatno());
			// student.setSemister(sheet.getSemister());
			sheet.setStudresult(student1);

			System.out.println("bean:::::" + sheet);
			commonService.saveResulMarksheet(sheet, subjectCode);

		}
		/*
		 * if (element.contains("SGPA")) {
		 *
		 * String str3 = element.replaceAll("\\s+", "");
		 *
		 * String sgpa = str3.substring(str3.indexOf(":") + 1, str3.indexOf(","));
		 *
		 * String l = str3.substring(str3.indexOf(","), str3.length() - 1);
		 *
		 * String earn_credit = str3.substring(str3.indexOf(","), str3.length());
		 *
		 * String total_earn = earn_credit.substring(earn_credit.indexOf(":") + 1,
		 * earn_credit.length()); sheet3.setSgpa(sgpa);
		 * sheet3.setEarn_credit(total_earn);
		 *
		 * commonService.saveMarkSheet(sheet3); sheet3 = null;
		 *
		 * }
		 */
	}

	public static String checkString(String str1)
	{
		System.out.println("testing functions:::::::::::");
		if (str1.contains("/"))
		{
			String c = str1.substring(0, str1.indexOf("/"));

			Pattern pt = Pattern.compile("[^0-9]");
			Matcher match = pt.matcher(c);
			while (match.find())
			{
				String s = match.group();
				c = c.replaceAll("\\" + s, "");
				System.out.println("testcase" + c);
			}
			return c;
		} else
		{
			return null;
		}
	}

}
