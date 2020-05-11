<!DOCTYPE html>
<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href="resources/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="resources/css/profile.css">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script>
        function sendFile() {
            // данные для отправки
            let formData = new FormData();
            // забрал файл из input
            let files = ($('#file'))[0]['files'];
            // добавляю файл в formData
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/profile/upload_avatar",
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    alert('Аватар обновлен');
                    window.location = "http://localhost:8080/profile";
                })
                .fail(function () {
                    alert('Неверный формат файла')
                });
        }
    </script>
</head>

<body>
<#include "header.ftl">
<@security.authorize access="hasAuthority('VOLUNTEER')">
    <#include "profileVolunteer.ftl">
</@security.authorize>
<@security.authorize access="hasAuthority('CONSUMER')">
    <#include "profileConsumer.ftl">
</@security.authorize>
</body>
</html>