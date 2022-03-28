package forever.young.admin.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forever.young.admin.service.AdminSalesService;
import forever.young.admin.vo.AdminSalesVO;

@Controller
public class AdminSalesController {

	@Autowired
	private AdminSalesService adminsalesservice;



	@RequestMapping(value = "monthExcel.mdo")
	public void monthExcel(AdminSalesVO adminsalesvo, HttpServletResponse response, Model model) throws Exception {
		List<AdminSalesVO> list = adminsalesservice.getSales(adminsalesvo);

		// ��ũ�� ����
		Workbook wb = new HSSFWorkbook();
		// ��Ʈ ����
		Sheet sheet = wb.createSheet("��ǰ�� ������Ȳ");
		// �� ����
		Row row = null;
		// �� ����
		Cell cell = null;

		int rowNo = 0;

		// ���̺� ��Ÿ�� �����ϱ�

		// ���̺� ����� ��Ÿ��
		CellStyle headStyle = wb.createCellStyle();

		// ���� ��輱
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);

		// ����
		headStyle.setFillForegroundColor(HSSFColorPredefined.LIGHT_GREEN.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// ������ ��� ����
		headStyle.setAlignment(HorizontalAlignment.CENTER);

		// �����Ϳ� ��� ��Ÿ�� �׵θ� ����
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);

		// �������
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("�ֹ���¥");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("��з�");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("�ߺз�");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("�Һз�");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("�귣���");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("��ǰ��");
		cell = row.createCell(6);
		cell.setCellStyle(headStyle);
		cell.setCellValue("��ǰ����");
		cell = row.createCell(7);
		cell.setCellStyle(headStyle);
		cell.setCellValue("�ֹ��Ǽ�");
		cell = row.createCell(8);
		cell.setCellStyle(headStyle);
		cell.setCellValue("����");

		// ������ �κ� ����
		for (AdminSalesVO vo : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getDATE1());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getCATEGORY_FIRST_NAME());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getCATEGORY_SECOND_NAME());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getCATEGORY_THIRD_NAME());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getCATEGORY_GOODS_BRAND());
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getCATEGORY_GOODS_NAME());
			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getORDER_GOODS_PRICE()+"��");
			cell = row.createCell(7);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getORDER_GOODS_COUNT()+"��");
			cell = row.createCell(8);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getPRICE()+"��");
		}

		// ������ Ÿ�԰� ���ϸ� ����
		String filename = "��ǰ�� ������Ȳ.xls";
		FileOutputStream fileOut = null;
		response.setContentType("application/octet-stream;");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		// ���� ����
		try {
			fileOut = new FileOutputStream("����.xls");
			wb.write(response.getOutputStream());
			wb.close();
			fileOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
		}
//		
//		response.getOutputStream().flush();
//		response.getOutputStream().close();
	}

}
