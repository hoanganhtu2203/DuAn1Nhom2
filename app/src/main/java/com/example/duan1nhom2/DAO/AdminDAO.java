package com.example.duan1nhom2.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom2.DTO.AdminDTO;
import com.example.duan1nhom2.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    SQLiteDatabase database;
    public AdminDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(AdminDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_ADMIN_HOTENNV,nhanVienDTO.getHOTENNV());
        contentValues.put(CreateDatabase.TBL_ADMIN_TENDN,nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TBL_ADMIN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TBL_ADMIN_EMAIL,nhanVienDTO.getEMAIL());
        contentValues.put(CreateDatabase.TBL_ADMIN_SDT,nhanVienDTO.getSDT());
        contentValues.put(CreateDatabase.TBL_ADMIN_GIOITINH,nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_MAQUYEN,nhanVienDTO.getMAQUYEN());

        long ktra = database.insert(CreateDatabase.TBL_ADMIN,null,contentValues);
        return ktra;
    }

    public long SuaNhanVien(AdminDTO nhanVienDTO,int manv){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_ADMIN_HOTENNV,nhanVienDTO.getHOTENNV());
        contentValues.put(CreateDatabase.TBL_ADMIN_TENDN,nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TBL_ADMIN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TBL_ADMIN_EMAIL,nhanVienDTO.getEMAIL());
        contentValues.put(CreateDatabase.TBL_ADMIN_SDT,nhanVienDTO.getSDT());
        contentValues.put(CreateDatabase.TBL_ADMIN_GIOITINH,nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_MAQUYEN,nhanVienDTO.getMAQUYEN());

        long ktra = database.update(CreateDatabase.TBL_ADMIN,contentValues,
                CreateDatabase.TBL_ADMIN_MANV+" = "+manv,null);
        return ktra;
    }

    @SuppressLint("Range")
    public int KiemTraDN(String tenDN, String matKhau){
        String query = "SELECT * FROM " +CreateDatabase.TBL_ADMIN+ " WHERE "
                +CreateDatabase.TBL_ADMIN_TENDN +" = '"+ tenDN+"' AND "+CreateDatabase.TBL_ADMIN_MATKHAU +" = '" +matKhau +"'";
        int manv = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manv = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MANV)) ;
            cursor.moveToNext();
        }
        return manv;
    }

    public boolean KtraTonTaiNV(){
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN;
        Cursor cursor =database.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public List<AdminDTO> LayDSNV(){
        List<AdminDTO> nhanVienDTOS = new ArrayList<AdminDTO>();
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN;

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            AdminDTO nhanVienDTO = new AdminDTO();
            nhanVienDTO.setHOTENNV(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_HOTENNV)));
            nhanVienDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_EMAIL)));
            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_NGAYSINH)));
            nhanVienDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_SDT)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_TENDN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MATKHAU)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MANV)));
            nhanVienDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAQUYEN)));

            nhanVienDTOS.add(nhanVienDTO);
            cursor.moveToNext();
        }
        return nhanVienDTOS;
    }

    public boolean XoaNV(int manv){
        long ktra = database.delete(CreateDatabase.TBL_ADMIN,CreateDatabase.TBL_ADMIN_MANV+ " = " +manv
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public AdminDTO LayNVTheoMa(int manv){
        AdminDTO nhanVienDTO = new AdminDTO();
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN+" WHERE "+CreateDatabase.TBL_ADMIN_MANV+" = "+manv;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            nhanVienDTO.setHOTENNV(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_HOTENNV)));
            nhanVienDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_EMAIL)));
            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_NGAYSINH)));
            nhanVienDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_SDT)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_TENDN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MATKHAU)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MANV)));
            nhanVienDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAQUYEN)));

            cursor.moveToNext();
        }
        return nhanVienDTO;
    }

    @SuppressLint("Range")
    public int LayQuyenNV(int manv){
        int maquyen = 0;
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN+" WHERE "+CreateDatabase.TBL_ADMIN_MANV+" = "+manv;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maquyen = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAQUYEN));

            cursor.moveToNext();
        }
        return maquyen;
    }
}
