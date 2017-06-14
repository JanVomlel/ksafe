package cz.aipsafe.ksafe.gui.model

import cz.aipsafe.ksafe.gui.services.Services
import cz.aipsafe.ksafe.gui.model.login.LoginModel


class Model(val services: Services) {
    val login = LoginModel(services.loginService)
}