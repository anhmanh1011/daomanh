$( document ).ready(function() {
   
    $('.btn-gioHang').click(function () {
        var maSanPham = $('#ChiTietSanPham-TenSanPham').attr("data-value");
        var maMau = $(this).closest('tr').find('.mau').attr('data-mamau');
        var soLuong = $(this).closest('tr').find('.soluong').attr('data-soluong');
        var maChitiet = $(this).attr('data-chitiet');

        $.ajax({
            url: '/api/themGioHang',
            type: 'Get',
            data: {
                maSp: maSanPham,
                maMau: maMau,
                soLuong: soLuong,
                maChiTiet: maChitiet

            },
            success: function (data) {

                if(   $('#gioHang').find('div').class == "icon-giohang"){
                    $('#gioHang').find('span').html(data);
                }else {
                    $('#gioHang').find('div').addClass("icon-giohang");
                    $('#gioHang').find('div').html("<span> " + data + "</span>");
                }
            }


        });
    });
    loadSp();

    function loadSp(isEvenChange) {
        var  tongTiensp = 0;

        $('.giaTien').each(function () {

            var soLuong = $(this).closest('tr').find('.soluong').val();
            var giatien = parseFloat($(this).attr('data-giatien')) * soLuong;

            var format=  parseFloat(giatien).toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.").toString();
            // alert(parseFloat(tongTiensp + parseFloat(format)))
            var tong = tongTiensp + giatien;
            tongTiensp = tong;

            if(!isEvenChange )
            {

                $(this).html(format);
            }


        })
        var formatTongTien = tongTiensp.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.").toString()

        $('#tongtien').html(formatTongTien + "");


    }


    $('.soluong').change(function () {


        var soluong = $(this).val();
        var masp = $(this).closest('tr').find('.sanpham').attr('data-masanpham');
        var maMau = $(this).closest('tr').find('.mau').attr('data-mamau');

        var giatien = $(this).closest('tr').find('.giaTien').attr('data-giatien');
        var tongTien = soluong * parseFloat(giatien);

        var format = tongTien.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");

        $(this).closest('tr').find('.giaTien').html(format);
        loadSp(true);

        $.ajax({
            url: '/api/updateGioHang',
            type: 'Get',
            data: {
                maSp: masp,
                maMau: maMau,
                soLuong: soluong
            },
            success: function (data) {

            }


        });
    });


    $('.xoa-giohang').click(function () {
        var masp = $(this).closest('tr').find('.sanpham').attr('data-masanpham');
        var maMau = $(this).closest('tr').find('.mau').attr('data-mamau');
        var del = $(this);

        $.ajax({
            url: '/api/deleteGioHang',
            type: 'Get',
            data: {
                maSp: masp,
                maMau: maMau,

            },
            success: function (data) {

                if (data === 'success') {
                    alert(data);
                    del.closest('tr').remove();
                    loadSp(true);
                }

            }


        });
    });




    
    
});