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
    
    
    
});