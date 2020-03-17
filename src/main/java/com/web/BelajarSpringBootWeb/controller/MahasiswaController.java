package com.web.BelajarSpringBootWeb.controller;

import com.web.BelajarSpringBootWeb.model.Mahasiswa;
import com.web.BelajarSpringBootWeb.services.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MahasiswaController {

    private MahasiswaService mahasiswaService;

    @Autowired//inject dari MahasiswaService
    public void setMahasiswaService(MahasiswaService mahasiswaService) {
        this.mahasiswaService = mahasiswaService;
    }

    @RequestMapping("/mahasiswa")
    public String MahasiswaList(Model model){
        model.addAttribute("mahasiswa", mahasiswaService.listMahasiswa());
        return "mahasiswa";
    }

    @RequestMapping(value = "/mahasiswa/create", method = RequestMethod.GET)
    public String tampilkanForm(Model model){
        model.addAttribute("mahasiswa", new Mahasiswa());
        return "formMahasiswa";
    }

    @RequestMapping(value = "/mahasiswa/create", method = RequestMethod.POST)
    public String simpanDataMahasiswa(Model model, Mahasiswa mahasiswa){
        model.addAttribute("mahasiswa",mahasiswaService.saveOrUpdate(mahasiswa));
        return "redirect:/mahasiswa";
    }

    @RequestMapping(value = "/mahasiswa/edit/{id}", method = RequestMethod.GET)
    public String editData(@PathVariable Integer id, Model model){
        model.addAttribute("mahasiswa",mahasiswaService.getIdMahasiswa(id));
        return "formMahasiswa";
    }

    @RequestMapping(value = "/mahasiswa/hapus/{id}")
    public String hapusData(@PathVariable Integer id){
        mahasiswaService.hapus(id);
        return "redirect:/mahasiswa";
    }
}
