<?php
defined('BASEPATH') or exit('No direct script access allowed');


class Buku extends AUTH_Controller
{

  public function __construct()
  {
    parent::__construct();
    $this->load->model('M_buku');
  }

  public function index()
  {
    $data['userdata'] = $this->userdata;
    $data['dataBuku'] = $this->M_buku->select_all();

    $data['page'] = "buku";
    $data['judul'] = "Data Buku";
    $data['deskripsi'] = "Manage Data Buku";

    $data['modal_tambah_buku'] = show_my_modal('modals/modal_tambah_buku', 'tambah-buku', $data);

    $this->template->views('buku/home', $data);
  }

  public function tampil()
  {
    $data['dataBuku'] = $this->M_buku->select_all();
    $this->load->view('buku/list_data', $data);
  }

  public function prosesTambah()
  {
    $this->form_validation->set_rules('name', 'Nama', 'trim|required');
    $this->form_validation->set_rules('author', 'Author', 'trim|required');
    $this->form_validation->set_rules('publisher', 'Publisher', 'trim|required');
    $this->form_validation->set_rules('synopsis', 'Sinopsis', 'trim|required');
    $this->form_validation->set_rules('genre', 'Genre', 'trim|required');

    $data = $this->input->post();

    if ($this->form_validation->run() == TRUE) {
      $config['upload_path'] = './assets/img/books/';
      $config['allowed_types'] = 'jpg|png';
      $config['overwrite'] = TRUE;
      $this->load->library('upload', $config);
      if (!$this->upload->do_upload('foto')) {
        $error = array('error' => $this->upload->display_errors());
        print_r($error);
      } else {
        $data_foto = $this->upload->data();
        $data['foto'] = $data_foto['file_name'];
      }

      $config2['upload_path'] = './assets/pdf/';
      $config2['allowed_types'] = 'pdf';
      $config2['overwrite'] = TRUE;
      $this->upload->initialize($config2);
      if (!$this->upload->do_upload('pdf')) {
        $error = array('error' => $this->upload->display_errors());
        print_r($error);
      } else {
        $data_pdf = $this->upload->data();
        $data['pdf'] = $data_pdf['file_name'];
      }

      $result = $this->M_buku->insert($data);

      if ($result > 0) {
        $out['status'] = '';
        $out['msg'] = show_succ_msg('Data Buku Berhasil ditambahkan', '20px');
      } else {
        $out['status'] = '';
        $out['msg'] = show_err_msg('Data Buku Gagal ditambahkan', '20px');
      }
    } else {
      $out['status'] = 'form';
      $out['msg'] = show_err_msg(validation_errors());
    }

    echo json_encode($out);
  }

  public function update()
  {
    $id = trim($_POST['id']);

    $data['dataBuku'] = $this->M_buku->select_by_id($id);
    $data['userdata'] = $this->userdata;

    echo show_my_modal('modals/modal_update_buku', 'update-buku', $data);
  }

  public function prosesUpdate()
  {
    $this->form_validation->set_rules('name', 'Nama', 'trim|required');
    $this->form_validation->set_rules('author', 'Author', 'trim|required');
    $this->form_validation->set_rules('publisher', 'Publisher', 'trim|required');
    $this->form_validation->set_rules('synopsis', 'Sinopsis', 'trim|required');

    $data = $this->input->post();

    if ($this->form_validation->run() == TRUE) {
      $config['upload_path'] = './assets/img/books/';
      $config['allowed_types'] = 'jpg|png';
      $config['overwrite'] = TRUE;
      $this->load->library('upload', $config);
      if (!$this->upload->do_upload('foto')) {
        $error = array('error' => $this->upload->display_errors());
        print_r($error);
      } else {
        $data_foto = $this->upload->data();
        $data['foto'] = $data_foto['file_name'];
      }

      $config2['upload_path'] = './assets/pdf/';
      $config2['allowed_types'] = 'pdf';
      $config2['overwrite'] = TRUE;
      $this->upload->initialize($config2);
      if ($this->upload->do_upload('pdf')) {
        $error = array('error' => $this->upload->display_errors());
        print_r($error);
      } else {
        $data_pdf = $this->upload->data();
        $data['pdf'] = $data_pdf['file_name'];
      }
      $result = $this->M_buku->update($data);

      if ($result > 0) {
        $out['status'] = '';
        $out['msg'] = show_succ_msg('Data Buku Berhasil diupdate', '20px');
      } else {
        $out['status'] = '';
        $out['msg'] = show_succ_msg('Data Buku Gagal diupdate', '20px');
      }
    } else {
      $out['status'] = 'form';
      $out['msg'] = show_err_msg(validation_errors());
    }

    echo json_encode($out);
  }

  public function delete()
  {
    $id = $_POST['id'];
    $result = $this->M_buku->delete($id);

    if ($result > 0) {
      echo show_succ_msg('Data Buku Berhasil dihapus', '20px');
    } else {
      echo show_err_msg('Data Buku Gagal dihapus', '20px');
    }
  }
}
