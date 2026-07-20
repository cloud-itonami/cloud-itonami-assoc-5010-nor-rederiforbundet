# ADR 0001: Kotoba is the Rederiforbundet catalog source authority

- Status: Accepted
- Date: 2026-07-21

## Decision

`src/association_facts.kotoba` is the sole production source. Both citations
retain every present scalar field. The founding citation retains complete date
`1909-09-15`, while the award retains year-only `1999`. Topic count plus indexed
access preserves governance and environment. Unknown values and indexes return
zero or typed option-none; no effects are declared.

CI executes reference semantics, restricted JavaScript, instantiated typed
WebAssembly, and production source-authority checks. Clojure and the JVM are
compiler/test hosts only.

## Consequences

- Complete and year-only dates retain their source precision.
- The catalog ABI is bounded, typed, deterministic, and fail-closed.
