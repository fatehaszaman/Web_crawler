# Web Crawler

Multithreaded Java web crawler built for a university Network Privacy Lab, supporting deep learning website fingerprinting research published at IEEE ICDCS 2020.

The crawler was part of the lab's Docker-based cloud pipeline and served as the primary data collection tool for building large-scale URL datasets used in CNN/LSTM-based traffic analysis experiments. Reworking the crawl logic produced roughly a 60% improvement in output volume, validity, and run time compared to the prior approach.

## Files

`WebCrawler.java` is the core. BFS-based crawler using a URL queue and visited set. Fetches raw HTML, extracts links via regex, filters by domain and a configurable exclusion list, and collects up to a specified URL cap (tested up to 50,000 URLs).

`URLScraper.java` scrapes the target site's homepage and samples article URLs matching a structured date/section pattern. Deduplicates via HashSet and collects up to 1,000 unique URLs per run.

`NYTimesURLGenerator.java` probes URL patterns via HTTP response codes to surface valid article endpoints.

`PrintLinksInAlphabeticalOrder.java` post-processes a collected link file: extracts href targets via regex, sorts alphabetically, reports count.

`UniqueLinks.java` deduplication pass over raw link output; filters to valid article URLs using HashSet.

`Web-Crawler-Trial-*.py` are the Python prototypes from early iteration before the Java implementation took over.

## Stack

Java, Python, regex-based HTML parsing, BFS traversal, HashSet deduplication

## Context

Part of a broader pipeline (300k+ URLs per source) used to build training and evaluation datasets for website fingerprinting models. Pipeline tests and documentation were written to enable reuse across other lab research efforts.
