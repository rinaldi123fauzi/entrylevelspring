package com.web.BelajarSpringBootWeb.dao;

import com.web.BelajarSpringBootWeb.model.Mahasiswa;
import com.web.BelajarSpringBootWeb.services.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class MahasiswaDao implements MahasiswaService {

    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Mahasiswa> listMahasiswa() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Mahasiswa", Mahasiswa.class).getResultList();
    }


    //untuk menyimpan baru atau update
    @Override
    public Mahasiswa saveOrUpdate(Mahasiswa mahasiswa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();//proses dimulai
        Mahasiswa saved = em.merge(mahasiswa);//berfungsi sebagai verifikator untuk update dan create data
        em.getTransaction().commit();
        return saved;
    }

    //untuk menarik ke form edit
    @Override
    public Mahasiswa getIdMahasiswa(Integer id){
        EntityManager em = emf.createEntityManager();
        return em.find(Mahasiswa.class, id);
    }

    //untuk menghapus data
    @Override
    public void hapus(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Mahasiswa.class, id));
        em.getTransaction().commit();
    }
}
