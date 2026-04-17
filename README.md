# Web Crawler & Link Graph Research Tool

Multithreaded Java web crawler built for the **Network Privacy Lab at Loyola University Chicago**, supporting Dr. Eric Chan-Tin's deep learning website fingerprinting research (published at IEEE ICDCS 2020).

The crawler was a required component of the lab's Docker-based cloud deployment and served as the primary data-collection infrastructure for building large-scale URL datasets used in CNN/LSTM-based traffic analysis experiments.

## What it does

- **`WebCrawler.java`** — BFS-based crawler using a URL queue and visited set. Fetches raw HTML, extracts links via regex, filters by domain and a configurable exclusion list, and collects up to a specified URL cap (tested up to 50,000 URLs). Built to target NYT article URLs for the fingerprinting dataset.
- **`URLScraper.java`** — Scrapes the NYT homepage and samples random article URLs matching a structured date/section pattern. Deduplicates via HashSet; collects up to 1,000 unique article URLs per run.
- **`NYTimesURLGenerator.java`** — Utility for probing URL patterns via HTTP response codes to surface valid article endpoints.
- **`PrintLinksInAlphabeticalOrder.java`** — Post-processing utility: reads a collected link file, extracts `<a href>` targets via regex, sorts alphabetically, and reports count.
- **`UniqueLinks.java`** — Deduplication pass over a raw link file; filters to valid NYT `.html` article URLs using HashSet.
- **`Web-Crawler-Trial-*.py`** — Python prototypes developed during early iteration before the Java implementation.

## Stack

Java · Python · Regex-based HTML parsing · BFS traversal · HashSet deduplication

## Context

Part of a broader pipeline (300k+ URLs per source) used to build training and evaluation datasets for website fingerprinting models. Reworking the crawl logic produced ~60% improvement in output volume, validity, and run time vs. the prior approach. Pipeline tests and documentation enabled reuse across other lab research efforts.
