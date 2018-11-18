$(document).ready(function () {

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

                if ($('#gioHang').find('div').class == "icon-giohang") {
                    $('#gioHang').find('span').html(data);
                } else {
                    $('#gioHang').find('div').addClass("icon-giohang");
                    $('#gioHang').find('div').html("<span> " + data + "</span>");
                }
            }


        });
    });
    loadSp();

    function loadSp(isEvenChange) {
        var tongTiensp = 0;

        $('.giaTien').each(function () {

            var soLuong = $(this).closest('tr').find('.soluong').val();
            var giatien = parseFloat($(this).attr('data-giatien')) * soLuong;

            var format = parseFloat(giatien).toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.").toString();
            // alert(parseFloat(tongTiensp + parseFloat(format)))
            var tong = tongTiensp + giatien;
            tongTiensp = tong;

            if (!isEvenChange) {

                $(this).html(format);
            }


        });
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
                    // alert(data);
                    del.closest('tr').remove();
                    loadSp(true);
                }

            }


        });
    });


    $('body').on('click', '.page-item', function () {

        $('.page-item').removeClass('active');
        $(this).addClass('active');

        var pageNum = $(this).text();
        $.ajax({
            url: '/api/themsanpham',
            type: 'Get',
            data: {
                pageNumber: pageNum
            },

            success: function (data) {


                var bodySanPham = $('tbody');
                bodySanPham.empty();
                bodySanPham.append(data);
            }


        });


    });

    $('#checkAll').change(function () {

        if (this.checked) {
            $('#table-sanpham input').each(function () {
                $(this).attr('checked', true);
            })
        }
        else {
            $('#table-sanpham input').each(function () {
                $(this).attr('checked', false);
            })

        }


    });


    $('#xoaSanPham').click(function () {

        var check = $('tbody input:checked');
        check.each(function () {
            var masanpham = this.value;
            var This = $(this);
            $.ajax({
                url: '/api/xoasanpham',
                type: 'Get',
                data: {
                    maSanPham: masanpham
                },

                success: function (data) {

                    This.closest('tr').remove();

                }


            });

        })


    });


    var files = [];
    var hinhSanPham = "";

    $('#file').change(function (event) {

        files = event.target.files;
        hinhSanPham = files[0].name;
        console.log(hinhSanPham);
        forms = new FormData();
        forms.append('hinhanh', files[0]);

        $.ajax({
            url: '/api/upLoadFile',
            type: 'Post',
            data: forms,
            contentType: false,
            processData: false,
            enctype: "multipart/form-data",

            success: function (data) {

                $('#hinhSanPham').val(hinhSanPham);

            }


        });

    });




    $('body').on('click', '.btn-xoaChiTiet', function () {

        var a = $(this).closest('div');
        a.remove();
        refesh();


    });


    function refesh() {

        var i = 0;
        var j = 0;
        $('#danhSachChiTiet').find('.Mau').each(function () {
            $(this).attr('name', 'dsChiTietSanPham[' + i + '].mauSanPham.maMau');
            i++;


        });
        $('#danhSachChiTiet').find('.soLuong').each(function () {
            $(this).attr('name', 'dsChiTietSanPham[' + j + '].soLuong');

            j++;
        });

    }

    $('.btn-themChiTiet').click(function () {

        var themChiTiet = $('#chiTiet').clone().removeAttr('id');


        $('#danhSachChiTiet').append(themChiTiet);
        refesh();


    });

    $('#form-themSanPham').submit(function (e) {
        e.preventDefault();
    });


    $('#themSanPham').click(function (e) {
        //
        // var tensp = $('input[name=tenSanPham]').val();
        // var giaTien = $('input[name=giaTien]').val();
        // var moTa = $('textarea[name=moTa]').val();
        // var maDanhMuc = $('select[name=maDanhMuc]').val();
        // var maMau=[];
        // var i = 0;
        // $('#danhSachChiTiet').find('.Mau').each(function () {
        //    maMau[i] = $(this).val();
        //
        //     alert(maMau[i]);
        // });


        e.preventDefault();

        // alert($(this).val());



        $.ajax({
            url: '/api/SaveSanPham',
            type: 'Post',
            data:
                $('#form-themSanPham').serialize(),
            hinhSanPham: hinhSanPham

            ,

            success: function (data) {

                alert(data);
                location.reload();




            }


        });


    });

    $('body').on('click', '.btnSua', function () {
        var maSanPham = $(this).attr('data-maSanPham');


        $.ajax({
            url: '/api/detailSanPham',
            type: 'Post',
            data: {
                maSanPham: maSanPham

            }
            ,

            success: function (data) {

                console.log(data);

                $("input[name$='tenSanPham']").val(data.tenSanPham);
                $("input[name$='giaTien']").val(data.giaTien);
                $("input[name$='hinhSanPham']").val(data.hinhSanPham);
                $("textarea[name$='moTa']").val(data.moTa);
                $("select[name$='danhMucSanPham.maDanhMuc']").val(data.danhMucSanPham.maDanhMuc);

                $('#danhSachChiTiet').empty();
                for (var i = 0; i < data.dsChiTietSanPham.length; i++) {
                    var themChiTiet = $('#chiTiet').clone().removeAttr('id');


                    $('#danhSachChiTiet').append(themChiTiet);


                    refesh();

                    $("select[name$='dsChiTietSanPham[" + i + "].mauSanPham.maMau']").val(data.dsChiTietSanPham[i].mauSanPham.maMau);
                    $("input[name$='dsChiTietSanPham[" + i + "].soLuong']").val(data.dsChiTietSanPham[i].soLuong);


                }
                $('#themSanPham').hide();

                $('#maSanPham').val(maSanPham);
                $('#suaSanPham').removeAttr('hidden');

            }


        });


    });


    $('#suaSanPham').click(function (e) {

        e.preventDefault();


        console.log($('#form-themSanPham').serialize());

        masp = $(this).attr('data-maSanPham');

        $.ajax({
            url: '/api/updateSanPham',
            type: 'Post',
            data:
                $('#form-themSanPham').serialize(),


            success: function (data) {

                alert(data);
                $('#Refesh').click();

            }


        });

    });

});