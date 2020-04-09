<#ftl encoding="utf-8">
<#assign  security=JspTaglibs["/WEB-INF/security.tld"] />


<!DOCTYPE html>

<html lang="ru">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
  <link rel="stylesheet" href=resources/css/bootstrap-reboot.css">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>

</head>

<body>
<#include "header.ftl">
 <br>
<br>
  <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner" role="listbox">
      <div class="carousel-item active">
        <img class="d-block w-100" src="resources/img/carousel1.jpg" alt="Первый слайд" width="900" height="400">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="resources/img/carousel2.jpg" alt="Второй слайд" width="9000" height="400">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="resources/img/carousel3.jpg" alt="Третий слайд" width="9000" height="400">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <div class="container-fluid my-sm-3">
    <div class="row  col-12">
      <div class="card mx-auto my-sm-5" style="width: 18rem;">
        <div class="card-body">
          <h5 class="card-title text-center ">Наши волонтеры</h5>
          <h1 class="text-center text-primary">765</h1>
          <div class="form-row text-center">
            <div class="col-12">
              <a href="#" class="btn btn-warning stretched-link text-white">Перейти</a>
            </div>
          </div>
        </div>
      </div>
      <div class="card mx-auto my-sm-5" style="width: 18rem;">
        <div class="card-body">
          <h5 class="card-title text-center">Мероприятия</h5>
          <h1 class="text-center color text-primary">765</h1>
          <div class="col-center">
            <div class="form-row text-center">
              <div class="col-12">
                <a href="#" class="btn btn-warning stretched-link text-white">Перейти</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="jumbotron bg-light" >
    <h1 class="display-6 text-center">Стать волонтером легко</h1>
    <hr>
    <p class="lead text-center  ">
      <a class="btn btn-warning btn-lg text-white" href="#" role="button">Узнать больше</a>
    </p>
    <div class="container-fluid my-sm-3">
      <div class="row  col-12">
        <div class="card mx-auto my-sm-5" style="width: 18rem;">
          <img class="card-img-top" src="resources/img/want_help.png" alt="Card image cap">
          <div class="card-body">
            <h5 class="card-title text-center ">Хочешь помочь?</h5>
            <p class="card-text text-center">Вы хотите помочь и не знаете куда обратиться?
              Присоединяйтесь к нам</p>
            <h1 class="text-center text-primary"></h1>
            <div class="form-row text-center">
              <div class="col-12">
                <a href="signUp" class="btn btn-warning stretched-link text-white">Присоединиться</a>
              </div>
            </div>
          </div>
        </div>
        <div class="card mx-auto my-sm-5" style="width: 18rem;">
          <img class="card-img-top" src="resources/img/blue_round.png" alt="Card image cap">
          <div class="card-body">
            <h5 class="card-title text-center">Нужна помощь?</h5>
            <p class="card-text text-center">Присоединяйтесь к нам и волонтеры сами найдут вас!</p>
            <br>
            <div class="col-center">
              <div class="form-row text-center">
                <div class="col-12">
                  <a href="signUp" class="btn btn-warning stretched-link text-white">Присоединиться</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
  </div>
</body>
</html>