Ring-Accept-language
====

## Overview

Ring-Accept-language provides a middleware that sets a hash that convert an accept-language in a request header.

## Usage

Add a middleware after adding dependencies.

    (defn some-middleware
      [handler]
      (wrap-accept-language handler))

## License

Copyright © 2019 Studist Corporation

Distributed under the MIT License, the same as Ring.
