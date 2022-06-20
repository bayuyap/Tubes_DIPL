<div class="col-md-offset-1 col-md-10 col-md-offset-1 well">
  <div class="form-msg"></div>
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h3 style="display:block; text-align:center;">Tambah Data Buku</h3>

  <form id="form-tambah-buku"  enctype="multipart/form-data" method="POST">
    <div class="input-group form-group">
      <span class="input-group-addon" id="sizing-addon2">
        <i class="glyphicon glyphicon-user"></i>
      </span>
      <input type="text" class="form-control" placeholder="Nama Buku" name="name" aria-describedby="sizing-addon2">
    </div>
    <div class="input-group form-group">
      <span class="input-group-addon" id="sizing-addon2">
        <i class="glyphicon glyphicon-phone-alt"></i>
      </span>
      <input type="text" class="form-control" placeholder="Author" name="author" aria-describedby="sizing-addon2">
    </div>
    <div class="input-group form-group">
      <span class="input-group-addon" id="sizing-addon2">
        <i class="glyphicon glyphicon-user"></i>
      </span>
      <input type="text" class="form-control" placeholder="Publisher" name="publisher" aria-describedby="sizing-addon2">
    </div>
    <div class="input-group form-group">
      <span class="input-group-addon" id="sizing-addon2">
        <i class="glyphicon glyphicon-book"></i>
      </span>
      <textarea name="synopsis" placeholder="Sinopsis" cols="50" rows="4" form="form-tambah-buku"></textarea>
    </div>
    <div class="input-group form-group">
      <span class="input-group-addon" id="sizing-addon2">
        <i class="glyphicon glyphicon-user"></i>
      </span>
      <input type="text" class="form-control" placeholder="Genre" name="genre" aria-describedby="sizing-addon2">
    </div>
    <div class="input-group form-group">
              <label for="inputFoto" class="col-sm-2 control-label">Cover Buku</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" placeholder="Foto" name="foto">
              </div>
    </div>
    <div class="input-group form-group">
              <label for="inputPDF" class="col-sm-2 control-label">PDF Buku</label>
              <div class="col-sm-10">
                <input type="file" class="form-control" placeholder="PDF" name="pdf">
              </div>
    </div>
    <div class="form-group">
      <div class="col-md-12">
          <button type="submit" class="form-control btn btn-primary"> <i class="glyphicon glyphicon-ok"></i> Tambah Data</button>
      </div>
    </div>
  </form>
</div>

