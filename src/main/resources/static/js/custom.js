$( document ).ready(function() {
   
    $('.btn-gioHang').click(function () {
        var maSanPham = $('#ChiTietSanPham-TenSanPham').attr("data-value");
        var maMau = $(this).closest('tr').find('.mau').attr('data-mamau');
        var soLuong = $(this).closest('tr').find('.soluong').attr('data-soluong');

        //     $.ajax({
        //         url: '/api/themGioHang',
        //         type: 'Get',
        //
        //         data: {
        //             maSp: maSanPham,
        //             maMau: maMau;
        //     soLuong:soLuong
        //        },
        //     success : function (value) {
        //
        //     }
        //
        //
        // });


        $.ajax({
            url: '/api/themGioHang',
            type: 'Get',
            data: {
                maSp: maSanPham,
                maMau: maMau,
                soLuong: soLuong
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
            var giatien = $(this).text();
            var format=  parseFloat(giatien).toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.").toString();
            // alert(parseFloat(tongTiensp + parseFloat(format)))
            var tong = tongTiensp + parseFloat(format);
            tongTiensp = tong;

            if(!isEvenChange )
            {

                $(this).html(format);
            }



            var formatTongTien =  tong.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.").toString()

            $('#tongtien').html(formatTongTien + "");

        })


    }


    $('.soluong').change(function () {


        var soluong = $(this).val();
        var giatien = $(this).closest('tr').find('.giaTien').attr('data-giatien');
        var  tongTien = soluong * giatien;

        var format = tongTien.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");

        $(this).closest('tr').find('.giaTien').html(format);
        loadSp(true);


    })
    
    
});