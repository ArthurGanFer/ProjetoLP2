package com.br.lp2.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author 1147106
 */
public class PhotoUploader {
    
    private Part filePart;
    private ServletContext context;
    private String path;
    private String name;

    public PhotoUploader(Part filePart, ServletContext context) {
        this.filePart = filePart;
        this.context = context;
    }
    
    public boolean upload(String p) throws IOException{
        boolean result = false;
        this.path = p;
        
        String fileName = filePart.getSubmittedFileName();
        String mimeType = context.getMimeType(fileName);
        
        //Valida se é imagem
        if(mimeType!=null && mimeType.startsWith("image/") ){
            
            String extension = mimeType.split("/")[1];
            if(extension.equalsIgnoreCase("jpeg")) extension = "jpg";
            //Criando diretório caso não exista
            File dir = new File(path);
            if(!dir.exists()){
                System.out.println("Criando diretório: "+path);
                try{
                    dir.mkdirs();
                } catch( SecurityException ex){
                    ex.printStackTrace();
                }
            }
            
            OutputStream ous = null;
            InputStream content = null;
            
            name = Calendar.getInstance().getTime().getTime() + "."+ extension ;
            File file = new File(path + File.separator + name);
            
            try {
                ous = new FileOutputStream(file);
                content = filePart.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                while((read = content.read(bytes) ) != -1){
                    ous.write(bytes,0,read);
                }
                System.out.println("Upload com sucesso: "+file.getAbsolutePath());
                result = true;
                                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PhotoUploader.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(ous != null) ous.close();
                if(content != null) content.close(); 
            }
            
            
        } else {
            System.out.println("O arquivo não é imagem!");
        }
        
        return result;
    }

    public String getName() {
        return name;
    }
    
}
