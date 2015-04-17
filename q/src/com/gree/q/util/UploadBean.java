package com.gree.q.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class UploadBean {

	public UploadBean(String path) {
		sourcefile = new String[255];
		objectpath = "d:\\ipdupload\\";
		suffix = new String[255];
		objectfilename = new String[255];
		sis = null;
		description = new String[255];
		size = 0x500000L;
		count = 0;
		b = new byte[4096];
		successful = true;
		objectfile = new File[255];
		setObjectpath(path);
	}

	public UploadBean() {
		sourcefile = new String[255];
		objectpath = "d:\\ipdupload\\";
		suffix = new String[255];
		objectfilename = new String[255];
		sis = null;
		description = new String[255];
		size = 0x500000L;
		count = 0;
		b = new byte[4096];
		successful = true;
		objectfile = new File[255];
	}

	private boolean canTransfer(int i) {
		return true;
	}

	public int getCount() {
		return count;
	}

	public String[] getFileNames() {
		return objectfilename;
	}

	public File[] getFiles() {
		return objectfile;
	}

	public String getObjectpath() {
		return objectpath;
	}

	public String[] getSourcefile() {
		return sourcefile;
	}

	public void setObjectpath(String objectpath) {
		this.objectpath = objectpath;
	}
 
	public void setSourcefile(HttpServletRequest request) throws IOException {
		sis = request.getInputStream();
		int a = 0;
		int k = 0;
		String s = "";
		int cut = 10;
		while ((a = sis.readLine(b, 0, b.length)) != -1) {
			s = new String(b, 0, a);
			if ((k = s.indexOf("filename=")) != -1) {
				s = s.substring(k + 10);
				k = s.indexOf("\"");
				s = s.substring(0, k);
				s = s.replaceAll("\\\\", "/");
				k = s.lastIndexOf("/");
				s = s.substring(k + 1, s.length());
				sourcefile[count] = s;
				k = s.lastIndexOf(".");
				suffix[count] = s.substring(k + 1);
				if (canTransfer(count))
					transferfile(count, cut);
			}
			if (!successful)
				break;
			cut++;
			if (cut > 100) {
				cut = 10;
			}
		}
	}

	private void transferfile(int i, int cut) {
		String x = Long.toString((new Date()).getTime()) + cut;
		try {
			objectfilename[i] = x;
			FileOutputStream out = new FileOutputStream(objectpath
					+ objectfilename[i]);
			int a = 0;
			long hastransfered = 0L;
			String s = "";
			while ((a = sis.readLine(b, 0, b.length)) != -1) {
				s = new String(b, 0, a);
				if ((s.indexOf("Content-Type:")) != -1)
					break;
			}
			sis.readLine(b, 0, b.length);
			while ((a = sis.readLine(b, 0, b.length)) != -1) {
				s = new String(b, 0, a);
				if (b[0] == 45 && b[1] == 45 && b[2] == 45 && b[3] == 45
						&& b[4] == 45)
					break;
				out.write(b, 0, a);
				hastransfered += a;
				if (hastransfered >= size) {
					description[count] = "ERR The file "
							+ sourcefile[count]
							+ " is too large to transfer. The whole process is interrupted.";
					successful = false;
					break;
				}
			}
			if (successful)
				description[count] = "Right The file " + sourcefile[count]
						+ " has been transfered successfully.";
			count++;
			out.close();
			if (!successful) {
				sis.close();
				File tmp = new File(objectpath + objectfilename[count - 1]);
				tmp.delete();
			} else {
				objectfile[count-1] = new File(objectpath + objectfilename[count-1]);
			}
		} catch (IOException ioe) {
			description[i] = ioe.toString();
		}
	}

	private byte b[];

	private int count;

	public String description[];

	public String objectfilename[];

	public File objectfile[];

	public String objectpath;

	public ServletInputStream sis;

	public long size;

	public String sourcefile[];

	private boolean successful;

	public String suffix[];
}
