# vasco

> I am not afraid of the darkness. Real death is preferable to a life without living.

A simple cljx routing library.

## Usage

From Clojure:

```clojure
(ns your.clj.app
  (:require [vasco.core :refer [defendpoint])))
```

From ClojureScript:

```clojure
(ns your.cljs.app
  (:require [vasco.core :refer-macros [defendpoint])))
```

Then (from either Clojure or ClojureScript), you can define endpoints:

```clojure
(defendpoint home    "/")
(defendpoint users   "/users")
(defendpoint profile "/profile/:username")
```

And render the url template with the parameters filled in:

```clojure
(home-path)
;; "/"

(profile-path :username "john")
;; "/profile/john"
```

Right now there is absolutely no special treatment of the params.

## License

Copyright © 2014 John

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
