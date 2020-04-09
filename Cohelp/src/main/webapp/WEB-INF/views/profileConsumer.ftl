<div class="container emp-profile">
    <form method="post">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <img src="https://sun9-68.userapi.com/c206524/v206524703/cacf8/Z54zEK3QNmw.jpg" alt=""/>
                    <div class="file btn btn-lg btn-primary">
                        Change Photo
                        <input type="file" name="file"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="profile-head">
                    <h5>
                        ${user.surname} ${user.name}
                    </h5>
                    <h6>
                        Хороший человек
                    </h6>
                    <p class="proile-rating">RANKINGS : <span>8/10</span></p>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="true" color="black">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="active-tab" data-toggle="tab" href="#active" role="tab"
                               aria-controls="profile" aria-selected="false" color="black">Непринятые заявки</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="in_progress-tab" data-toggle="tab" href="#in_progress" role="tab"
                               aria-controls="profile" aria-selected="false" color="black">Выполняемые заявки</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="request-tab" data-toggle="tab" href="#request" role="tab"
                               aria-controls="profile" aria-selected="false" color="black">Оставить заявку</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-2">
                <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="profile-work">
                </div>
            </div>
            <div class="col-md-8">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <label>ФИ</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.surname} ${user.name}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Должность</label>
                            </div>
                            <div class="col-md-6">
                                <p>не Волонтер</p>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <label>Номер</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.phone}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Адрес </label>
                            </div>
                            <div class="col-md-6">
                                <p>Г. ${user.address.city}, район ${user.address.district}, ${user.address.street}
                                    , ${user.address.house}</p>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="active" role="tabpanel" aria-labelledby="active-tab">
                        <#list tasks as task>
                            <#if task.state == "ACTIVE">
                                <div class="row">
                                    <div class="card" style="width: 18rem;">
                                        <img class="card-img-top" src="..." alt="Card image cap">
                                        <div class="card-body">
                                            <h5 class="card-title">Задание</h5>
                                            <p class="card-text">${task.description}</p>
                                            <a href="#" class="btn btn-primary">К заданию</a>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </#list>
                    </div>
                    <div class="tab-pane fade" id="in_progress" role="tabpanel" aria-labelledby="in_progress-tab">
                        <#list tasks as task>
                            <#if task.state == "IN_PROGRESS">
                                <div class="row">
                                    <div class="card" style="width: 18rem;">
                                        <img class="card-img-top" src="..." alt="Card image cap">
                                        <div class="card-body">
                                            <h5 class="card-title">Задание</h5>
                                            <p class="card-text">${task.description}</p>
                                            <a href="#" class="btn btn-primary">К заданию</a>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </#list>
                    </div>
                    <div class="tab-pane fade" id="request" role="tabpanel" aria-labelledby="request-tab">
                    <form action="/createTask"  method="post">
                        <div class="form-group row">
                            <label for="email" class="col-md-4 col-form-label text-md-right">Описание</label>
                            <div class="col-md-6">
                                <input type="text" id="description" class="form-control" name="description" required autofocus>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="role" class="col-md-4 col-form-label text-md-right">Вы?</label>
                            <div class="col-md-6">
                                <select id="type" name="type" class="form-control" required>

                                    <option value="walking">Прогулка с питомцем</option>

                                    <option value="products">Доставка продуктов</option>

                                    <option value="medicines">Доставка лекарств</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="consumer" value="${user.id}">
                        <div class="col-md-6 offset-md-4">
                            <button type="submit" class="btn btn-warning">
                                Оставить заявку
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
</div>
