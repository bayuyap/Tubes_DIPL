<?php
$no = 1;
  foreach ($dataBuku as $buku) {
    ?>
    <tr>
    <td><?php echo $no; ?></td>
      <td style="min-width:230px;"><?php echo $buku->name; ?></td>
      <td><?php echo $buku->author; ?></td>
      <td><?php echo $buku->publisher; ?></td>
      <td><?php echo $buku->synopsis; ?></td>
      <td><img class="img-responsive img-rounded" src="<?php echo base_url(); ?>assets/img/books/<?php echo $buku->foto; ?>" alt="User profile picture"></td>
      <td class="text-center" style="min-width:230px;">
        <button class="btn btn-warning update-dataBuku" data-id="<?php echo $buku->id_book; ?>"><i class="glyphicon glyphicon-repeat"></i> Update</button>
        <button class="btn btn-danger konfirmasiHapus-buku" data-id="<?php echo $buku->id_book; ?>" data-toggle="modal" data-target="#konfirmasiHapus"><i class="glyphicon glyphicon-remove-sign"></i> Delete</button>
      </td>
    </tr>
    <?php
    $no++;
  }
?>