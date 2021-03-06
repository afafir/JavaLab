<div class="container emp-profile">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <img src="http://127.0.0.1:1222/${user.avatarLink}" alt=""/>
                </div>
            </div>
            <div class="file btn btn-lg btn-primary">
                Change Photo
                <input type="file" id="file" name="file"/>
                <button onclick="sendFile()">
                    load file
                </button>
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
                            <a class="nav-link" id="current-tab" data-toggle="tab" href="#current" role="tab"
                               aria-controls="profile" aria-selected="false" color="black">Текущие заявки</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="done-tab" data-toggle="tab" href="#done" role="tab"
                               aria-controls="profile" aria-selected="false" color="black">Выполненные заявки</a>
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
                                <@security.authorize access="hasAuthority('VOLUNTEER')">
                                    <p>Волонтер</p>
                                </@security.authorize>
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
                                <label>Адрес</label>
                            </div>
                            <div class="col-md-6">
                                <p>Г. ${user.address.city}, район ${user.address.district}, ${user.address.street}
                                    , ${user.address.house}</p>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="current" role="tabpanel" aria-labelledby="current-tab">
                        <#list user.tasks as task>
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
                    <div class="tab-pane fade" id="done" role="tabpanel" aria-labelledby="done-tab">
                        <#list user.tasks as task>
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

                </div>
            </div>
        </div>
</div>

