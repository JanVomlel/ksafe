package cz.aipsafe.ksafe.gui.main

import cz.aipsafe.ksafe.gui.components.root.RootComponent
import cz.aipsafe.ksafe.gui.services.HttpServices
import cz.aipsafe.ksafe.gui.setup.AppSetup
import cz.aipsafe.ksafe.gui.setup.ModuleSetup
import cz.aipsafe.ksafe.gui.setup.SearchPageSetup
import kotlin.browser.document

fun example() {

    val services = HttpServices("../ksafe-www")

    val app = AppSetup().apply {

        modules += ModuleSetup().apply {
            title = "Smlouvy"
            pages += SearchPageSetup().apply {
                title = "Moje smlouvy"
                query = "Smluva(user = \$USER)"
            }
            pages += SearchPageSetup().apply {
                title = "Najdi smlouvu"
                query = "Smluva()"
            }
        }
        modules += ModuleSetup().apply {
            title = "Faktury"
            pages += SearchPageSetup().apply {
                title = "Moje faktury"
                query = "Faktura(user = \$USER)"
            }
            pages += SearchPageSetup().apply {
                title = "Najdi fakturu"
                query = "Faktura()"
            }
        }
    }
    document.body!!.appendChild(RootComponent(app, services).root)

}
