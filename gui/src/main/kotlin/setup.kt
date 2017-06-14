package cz.aipsafe.ksafe.gui.setup

import cz.aipsafe.ksafe.gui.services.Services

/**
 * Application setup. Application contains modules, one of them is default.
 */
class AppSetup(
        var title: String = "SAFE",
        var defaultModuleIndex: Int = 0,
        var modules: List<ModuleSetup> = listOf<ModuleSetup>(),
        val services: Services
)

/**
 * Module setup. Module has title and contains pages. One page is default page.
 */
class ModuleSetup (
        var title: String = "",
        var defaultPageIndex: Int = 0,
        var pages: List<PageSetup> = listOf()
)

/**
 * Page setup. Use descendants of this abstract setup.
 */
abstract class PageSetup (
        var title: String = ""
)

/**
 * Setup for search page.
 */
class SearchPageSetup(
        title: String = "",
        var query: String = ""
): PageSetup(title)


