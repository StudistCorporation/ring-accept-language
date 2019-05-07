# Ring Accept-language
====

## Overview

Ring-Accept-language provides a middleware that parses the Accept-language request header and injects the value into the request hash.

## Usage

Add a middleware after adding dependencies.

```Clojure
(defn some-middleware
  [handler]
  (wrap-accept-language handler))
```

## License

Copyright © 2019 Studist Corporation

Distributed under the MIT License, the same as Ring.
