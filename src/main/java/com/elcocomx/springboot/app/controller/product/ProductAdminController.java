package com.elcocomx.springboot.app.controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import com.elcocomx.springboot.app.model.entity.banner.Banner;
import com.elcocomx.springboot.app.model.entity.category.Category;
import com.elcocomx.springboot.app.model.entity.images.Image;
import com.elcocomx.springboot.app.model.entity.product.Product;
import com.elcocomx.springboot.app.model.entity.product.ProductAdmin;
import com.elcocomx.springboot.app.service.banner.IBannerService;
import com.elcocomx.springboot.app.service.brand.IBrandService;
import com.elcocomx.springboot.app.service.category.ICategoryService;
import com.elcocomx.springboot.app.service.image.IImageService;
import com.elcocomx.springboot.app.service.product.IProductService;
import com.elcocomx.springboot.app.service.utility.IUploadFileService;

import javassist.expr.NewArray;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("admin")
public class ProductAdminController {
	@Autowired
	IProductService productService;
	
	@Autowired
	IBannerService bannerService;
	
	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	IBrandService brandService;
	
	@Autowired
	IImageService imageService;
		
	@Autowired
	IUploadFileService uploadFileService;
	
	@RequestMapping(value="/product/list", method=RequestMethod.GET)
	public String listProduct(Model model) {	
		model.addAttribute("titulo", "Listado de productos en Banner");		
		model.addAttribute("products", productService.getAllProductsInfo());
		model.addAttribute("fileDto", new ProductAdmin());
		return "products";
	}
	
	@RequestMapping(value="/product/addBanner/{id}" )
	public RedirectView addBanner(@PathVariable("id") int id) {
        Banner banner = new Banner();
        Product prod =  new Product();
        prod.setProductId(id);
        banner.setProduct(prod);  	
		boolean flag = bannerService.addBanner(banner);
                if (flag == false) {
                }
                RedirectView rv = new RedirectView();
        		rv.setContextRelative(true);  
                rv.setUrl("/admin/product/list");
                return rv;
	}
	
	
	@RequestMapping(value="/product/addProduct/{id}",method=RequestMethod.GET)
	public String addProduct(@PathVariable("id") int id,Model model) {
		Product prod =  new Product();
		   	
        prod = productService.getProductById(id);      	
		
		model.addAttribute("product", prod);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("brands", brandService.getAllBrands());
		ProductAdmin pa = new ProductAdmin();
		pa.setCurrentProduct(id);
		model.addAttribute("newImage", pa);
		model.addAttribute("loadImage", 1);
		
         return "showProduct";
	}
	
	@RequestMapping(value="/product/loadExcel/",method=RequestMethod.GET)
	public String loadExcel(Model model) {
		
         return "LoadExcel";
	}
	
	@RequestMapping(value = "/product/loadExcel", method=RequestMethod.POST)
	public String loadExcel(@RequestParam("file") MultipartFile file, BindingResult result, Model model) throws IOException {
		
		
		
		if (!file.isEmpty()) {
			System.out.println("dentro --------");
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		                
	   return "excelResult";
	}
	
	
	
	@RequestMapping(value="/product/addProduct/",method=RequestMethod.GET)
	public String addNewProduct(Model model) {
		Product prod =  new Product();				
		model.addAttribute("product", prod);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("brands", brandService.getAllBrands());
		ProductAdmin pa = new ProductAdmin();
		model.addAttribute("newImage", pa);
		model.addAttribute("loadImage", 0);
		
         return "AddProduct";
	}
	
	@RequestMapping(value="/product/deleteProductImage/{idProd}/{idImg}",method=RequestMethod.GET)
	public RedirectView addProduct(@PathVariable("idProd") int idProd,@PathVariable("idImg") int idImg,Model model) {
		
		Product prod =  new Product();
		if(idProd != 0) {        	
        	prod = productService.getProductById(idProd);
        }		
		
		List<Image>imagenes = prod.getImages();
		ArrayList<Image>imagenesTemp = new ArrayList<>();
		for (Image image : imagenes) {
			if(image.getImageId() != idImg) {
				imagenesTemp.add(image);
			}
			
		}
		prod.getImages().clear();
		prod.setImages(imagenesTemp);
		
		imageService.deleteImage(idImg);
		productService.updateProduct(prod);
	
		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);  
      rv.setUrl("/admin/product/addProduct/"+idProd);
      return rv;	
	}
	
	@RequestMapping(value = "/product/loadXlsProducts", method=RequestMethod.POST)
	public String addProduct(@Valid ProductAdmin product, BindingResult result, Model model) throws IOException {
		       					
	   String ruta  = product.getUrlNewImage();
			   
	   ArrayList<ProductAdmin> tryInserted =  readExcelFile(new File(ruta));
	   
	   model.addAttribute("tryInserted", tryInserted);
		                
	   return "excelResult";
	}
		
	@RequestMapping(value = "/product/addProduct/", method=RequestMethod.POST)
	public String addProduct(@Valid Product product, BindingResult result, Model model) {
		       	
				
	    productService.addProduct(product);
	        
        
		model.addAttribute("product", product);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("brands", brandService.getAllBrands());
		ProductAdmin pa = new ProductAdmin();
		pa.setCurrentProduct(product.getProductId());
		model.addAttribute("newImage", pa);
		model.addAttribute("loadImage", 1);
		
		                
          return "showProduct";
	}

	@RequestMapping(value = "/product/addImageProduct", method=RequestMethod.POST)
	public String addImageProduct(@Valid ProductAdmin productAdm, BindingResult result, Model model) {
		       	
        Product prod = productService.getProductById(productAdm.getCurrentProduct());
        Image img  = new Image();
        
        img.setImageUrl(productAdm.getUrlNewImage());        
        imageService.addImage(img);                
        prod.getImages().add(img);       
        productService.updateProduct(prod);
        
        model.addAttribute("product", prod);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("brands", brandService.getAllBrands());
		model.addAttribute("loadImage", 1);
		ProductAdmin pa = new ProductAdmin();
		pa.setCurrentProduct(prod.getProductId());
		model.addAttribute("newImage", pa);
		
		               
          return "showProduct";
	}
	
	
	
	    @RequestMapping(value = "/product/downloadPdf", method = RequestMethod.GET)
	    @ResponseBody
	    public void downloadSpreadsheet(HttpServletResponse response) throws Exception {
	        XSSFWorkbook wb = null;
	        try {
	            wb = createWorkbook();
	 
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-disposition", "attachment; filename=ProductsFile.xlsx");
	            wb.write(response.getOutputStream());
	        } catch (IOException ioe) {
	            throw new RuntimeException("Error writing spreadsheet to output stream");
	        } finally {
	            if (wb != null) {
	                wb.close();
	            }
	        }
	    }
	 
	    private XSSFWorkbook createWorkbook() throws IOException {
	        XSSFWorkbook wb = new XSSFWorkbook();
	        XSSFSheet sheet = wb.createSheet("Products");	       
	        XSSFSheet sheet2 = wb.createSheet("Instrucciones");
	 
	        writeHeaders(wb, sheet); 
	        
	        writeInstruccions(wb, sheet2); 
	 
	        return wb;
	    }
	 
	    private void writeInstruccions(XSSFWorkbook workbook, XSSFSheet sheet) {
	    	XSSFRow header = sheet.createRow(0);
		       
	        XSSFCell headerTitle= header.createCell(0);
	        headerTitle.setCellValue("Instrucciones de llenado ");
	        	        
	        XSSFRow campos = sheet.createRow(6);
	        XSSFCell headerCampos= campos.createCell(0);
	        headerCampos.setCellValue("Todos los campos son alfa numéricos a excepción de : ");
	               
	        XSSFRow pre = sheet.createRow(7);
	        XSSFCell cellPre= pre.createCell(0);
	        cellPre.setCellValue(" 1.- Precio : Solo deben ingresarse Números ");
	        
	        XSSFRow tam = sheet.createRow(8);
	        XSSFCell cellTam= tam.createCell(0);
	        cellTam.setCellValue(" 1.- Tamaño : El campo Tamaño solo acepta   CH o M o G ");
	    	
	        XSSFRow sta = sheet.createRow(9);
	        XSSFCell cellSta = sta.createCell(0);
	        cellSta.setCellValue(" 1.- Status : El campo Status solo acepta  bla bla ");
	        
	    }
	    
	    private void writeHeaders(XSSFWorkbook workbook, XSSFSheet sheet) {
	        XSSFRow header = sheet.createRow(0);
	       
	        XSSFCell headerTitle= header.createCell(0);
	        headerTitle.setCellValue("Titulo");
	        headerTitle.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerDescripcion = header.createCell(1);
	        headerDescripcion.setCellValue("Descripcion");
	        headerDescripcion.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerSKU= header.createCell(2);
	        headerSKU.setCellValue("SKU");
	        headerSKU.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerPrecio= header.createCell(3);
	        headerPrecio.setCellValue("Precio");
	        headerPrecio.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerTalla= header.createCell(4);
	        headerTalla.setCellValue("Tamaño");
	        headerTalla.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerUPC= header.createCell(5);
	        headerUPC.setCellValue("UPC");
	        headerUPC.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerSatCode= header.createCell(6);
	        headerSatCode.setCellValue("Sat code");
	        headerSatCode.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerLicencia= header.createCell(7);
	        headerLicencia.setCellValue("Licencia");
	        headerLicencia.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerStatus= header.createCell(8);
	        headerStatus.setCellValue("Estatus");
	        headerStatus.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerNombre= header.createCell(9);
	        headerNombre.setCellValue("Nombre");
	        headerNombre.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerImg1= header.createCell(10);
	        headerImg1.setCellValue("Imagen 1");
	        headerImg1.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerImg2= header.createCell(11);
	        headerImg2.setCellValue("Imagen 2");
	        headerImg2.setCellStyle(createHeaderStyle(workbook));
	        
	        XSSFCell headerImg3= header.createCell(12);
	        headerImg3.setCellValue("Imagen 3");
	        headerImg3.setCellStyle(createHeaderStyle(workbook));
	        
	        //for(int i = 1; i <= 10;i++ ) {
	        //	XSSFRow datos = sheet.createRow(i);
	        //
	        //XSSFCell datosTitle= datos.createCell(0);
	        //  datosTitle.setCellValue("Titulo"+ i);
	        //  
	        //  XSSFCell datosDescripcion = datos.createCell(1);
	        ////  datosDescripcion.setCellValue("Descripcion"+ i);
	        //
	        //  XSSFCell datosSKU= datos.createCell(2);
	        //  datosSKU.setCellValue("SKU"+ i);
	        //  
	        //  XSSFCell datosPrecio= datos.createCell(3);
	        //  datosPrecio.setCellValue(i);
	        //  
	        //////  XSSFCell datosTalla= datos.createCell(4);
	        // datosTalla.setCellValue("M");
	        //  
	        //  XSSFCell datosUPC= datos.createCell(5);
	        //  datosUPC.setCellValue("UPC"+ i);
	        //  
	        //  XSSFCell datosSatCode= datos.createCell(6);
	        //  datosSatCode.setCellValue("Sat code" + i);
	        //  
	        //  XSSFCell datosLicencia= datos.createCell(7);
	        //  datosLicencia.setCellValue("Licencia" + i);
	        //  
	        //  XSSFCell datosStatus= datos.createCell(8);
	        //  datosStatus.setCellValue("Estatus" + i);
	        //  
	        //  XSSFCell datosNombre= datos.createCell(9);
	        //  datosNombre.setCellValue("Nombre" + i);
	        	
	        //}
	        
	        
	        
	    }
	 
	    
	    
	    
	    private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
	        XSSFCellStyle style = workbook.createCellStyle();
	       
	        style.setWrapText(true);
	        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(53, 119, 192)));
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	 
	        style.setBorderRight(CellStyle.BORDER_THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderBottom(CellStyle.BORDER_THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderLeft(CellStyle.BORDER_THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderTop(CellStyle.BORDER_THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	 
	        Font font = workbook.createFont();
	        font.setFontName("Helvetica");
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	 
	        return style;
	    }
	
	    
	    private   ArrayList<ProductAdmin> readExcelFile(File excelFile) throws IOException{
	    	    	
	    	FileInputStream fis = new FileInputStream(excelFile);

	        // we create an XSSF Workbook object for our XLSX Excel File
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        // we get first sheet
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        
	        
	        ArrayList<ProductAdmin> insertados =  new ArrayList<ProductAdmin>();
	        int colError = -1;
	        
	        // we iterate on rows
	        Iterator<Row> rowIt = sheet.iterator();
	        boolean leer = false;
	        while(rowIt.hasNext()) {
	        	Row row = rowIt.next();
	        	if(leer) {
	        		Product prod =  new Product();
	        		ProductAdmin prodA =  new ProductAdmin();
		        	
	        		if(validaString(row.getCell(0).toString())) {
	        			prod.setProductTitle(row.getCell(0).toString());
	        			prodA.setProductTitle(row.getCell(0).toString());
	        		} else {
	        			colError = 0;
	        		}
		        	
	        		
	        		if(validaString(row.getCell(1).toString())) {
	        			prod.setProductDescription(row.getCell(1).toString());
	        			prodA.setProductDescription(row.getCell(1).toString());
	        		} else {
	        			colError = 1;
	        		}
		        	
	        		if(validaString(row.getCell(2).toString())) {
	        			prod.setProductSku(row.getCell(2).toString());
	        			prodA.setProductSku(row.getCell(2).toString());
	        		} else {
	        			colError = 2;
	        		}
	        		
	        		
	        		if(validaDouble(row.getCell(3).toString())) {
	        			prod.setProductPrice(Double.parseDouble(row.getCell(3).toString()));
	        			prodA.setProductPrice(Double.parseDouble(row.getCell(3).toString()));
	        		} else {
	        			colError = 3;
	        		}
	        		
	        		
	        		if(validaString(row.getCell(4).toString())) {
	        			prod.setProductSize(row.getCell(4).toString());
	        			prodA.setProductSize(row.getCell(4).toString());
	        		} else {
	        			colError = 4;
	        		}
	        		
	        		if(validaString(row.getCell(5).toString())) {
	        			prod.setProductUpc(row.getCell(5).toString());
	        			prodA.setProductUpc(row.getCell(5).toString());
	        		} else {
	        			colError = 5;
	        		}
		        	
	        		if(validaString(row.getCell(6).toString())) {
	        			prod.setProductSatCode(row.getCell(6).toString());
	        			prodA.setProductSatCode(row.getCell(6).toString());
	        		} else {
	        			colError = 6;
	        		}
	        		
	        		if(validaString(row.getCell(7).toString())) {
	        			prod.setProductLicence(row.getCell(7).toString());
	        			prodA.setProductLicence(row.getCell(7).toString());
	        		} else {
	        			colError = 7;
	        		}
	        		
	        		if(validaString(row.getCell(8).toString())) {
	        			prod.setProductStatus(row.getCell(8).toString());
	        			prodA.setProductStatus(row.getCell(8).toString());
	        		} else {
	        			colError = 8;
	        		}
	        		

	        		if(validaString(row.getCell(9).toString())) {
	        			prod.setProductName(row.getCell(9).toString());
	        			prodA.setProductName(row.getCell(9).toString());
	        		} else {
	        			colError = 9;
	        		}
	        		
		        	
	        		if(colError < 0){ // si no exixte error       			
	        			
	        		
	        			ArrayList<Image> imagenesToAdd  = new ArrayList<Image>();
	        			
	        			try {
	        				if(row.getCell(10) != null)
	    	        			if(validaString(row.getCell(10).toString())) {
	    	        				Image img  = new Image();
	    	        				img.setImageUrl(row.getCell(10).toString());        
	    	        		        imageService.addImage(img);                
	    	        		        imagenesToAdd.add(img);     
	    	        			}
	    	        			
	    	        			if(row.getCell(11) != null)
	    	        			if(validaString(row.getCell(11).toString())) {
	    	        				Image img2  = new Image();
	    	        				img2.setImageUrl(row.getCell(11).toString());        
	    	        		        imageService.addImage(img2);                
	    	        		        imagenesToAdd.add(img2);     
	    	        			}
	    	        			
	    	        			if(row.getCell(12) != null)
	    	        			if(validaString(row.getCell(12).toString())) {
	    	        				Image img3  = new Image();
	    	        				img3.setImageUrl(row.getCell(12).toString());        
	    	        		        imageService.addImage(img3);                
	    	        		        imagenesToAdd.add(img3);     
	    	        			}
	    	        			prod.setImages(imagenesToAdd);   			
	    	        			
	    	        			if(productService.addProduct(prod)) { // si el producto se insertó correctamente 
	    	        				// se agrega producto a la lista de exitosos
	    	        				prodA.setMessageError("Insertado correctamente");
	    	        				prodA.setErrorCode(0);
	    	        				insertados.add(prodA);
	    	        			}else { // si hubo error al insertar el producto 
	    	        				// se agrega producto a la lista de erroneos	        				
	    	        				prodA.setMessageError("Error al insertar producto Verifique los datos ");
	    	        				prodA.setErrorCode(1);
	    	        				insertados.add(prodA);
	    	        			}
	        			}catch(Exception e ) {
	        				prodA.setMessageError("Error al insertar producto Verifique los datos ");
	        				prodA.setErrorCode(1);
	        				insertados.add(prodA);	        				
	        			}
	        			
	        			
	        		}else { // si existe error agrega el producto a la lista de errores 
	        			
	        			prodA.setErrorCode(2);
	        			prodA.setMessageError("Error al insertar producto Verifique la columna " + (colError + 1));
        				insertados.add(prodA);
	        		}
		  
		        	
		        	colError = -1;
	        	}     
	        	
	        	leer  = true;
	        }

	        
	        workbook.close();
	        fis.close();
	        
	       
	        
	        return insertados;
	      }
	    
	    	
public boolean validaString(String cadena) {
	boolean res = false;
	
	if(!cadena.trim().isEmpty()) {
		res = true;
	}	
	return res;
}
	    
	
public boolean validaDouble(String cadena) {
	boolean res = false;
	
	try{		
		if(!cadena.trim().isEmpty())
		Double.parseDouble(cadena.trim());
		
		res = true;
	}catch(Exception e) {
		res = false;
	}	
	return res;
}

	
}


